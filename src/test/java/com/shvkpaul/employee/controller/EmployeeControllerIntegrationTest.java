package com.shvkpaul.employee.controller;

import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
import com.shvkpaul.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeRepository employeeRepository;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/employees");
    }

    @Test
    void createEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest("S Paul", 1l);

        EmployeeResponse response = restTemplate.postForObject(baseUrl, employeeRequest, EmployeeResponse.class);

        assertEquals("S Paul", response.getName());
        assertEquals(1l, response.getRoleId());
    }

    @Test
    void getEmployeeById() {
        EmployeeRequest employeeRequest = new EmployeeRequest("S Paul", 1L);
        EmployeeResponse createdEmployee = restTemplate.postForObject(baseUrl, employeeRequest, EmployeeResponse.class);

        EmployeeResponse response = restTemplate.getForObject(baseUrl + "/" + createdEmployee.getId(), EmployeeResponse.class);

        assertEquals(createdEmployee.getId(), response.getId());
        assertEquals("S Paul", response.getName());
        assertEquals(1L, response.getRoleId());
    }

    @Test
    void getEmployeeByInvalidId() {
        Long invalidEmployeeId = 1000L;

        Exception exception = assertThrows(HttpClientErrorException.class, () ->
            restTemplate.getForObject(baseUrl + "/" + invalidEmployeeId, EmployeeResponse.class));

        assertEquals(HttpStatus.NOT_FOUND, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    void updateEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest("S Paul", 2L);
        EmployeeResponse createdEmployee = restTemplate.postForObject(baseUrl, employeeRequest, EmployeeResponse.class);

        EmployeeRequest updatedRequest = new EmployeeRequest("S Paul", 2L);
        restTemplate.put(baseUrl + "/" + createdEmployee.getId(), updatedRequest);

        EmployeeResponse updatedEmployee = restTemplate.getForObject(baseUrl + "/" + createdEmployee.getId(), EmployeeResponse.class);

        assertNotNull(updatedEmployee);
        assertEquals("S Paul", updatedEmployee.getName());
        assertEquals(2L, updatedEmployee.getRoleId());
    }

    @Test
    void deleteEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest("S Paul", 1L);
        EmployeeResponse createdEmployee = restTemplate.postForObject(baseUrl, employeeRequest, EmployeeResponse.class);

        restTemplate.delete(baseUrl + "/" + createdEmployee.getId());
    }

    @Test
    void createEmployeeWithInvalidData() {
        EmployeeRequest invalidEmployeeRequest = new EmployeeRequest("", 1L);
        try {
            restTemplate.postForObject(baseUrl, invalidEmployeeRequest, EmployeeResponse.class);
        } catch (Exception e) {
            assertEquals(e.getClass().getName(), "org.springframework.web.client.HttpClientErrorException$BadRequest");
        }
    }

}
