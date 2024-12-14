package com.shvkpaul.employee.service;

import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Role;
import com.shvkpaul.employee.mapper.EmployeeMapper;
import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
import com.shvkpaul.employee.repository.EmployeeRepository;
import com.shvkpaul.employee.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("S Paul");
        employeeRequest.setRoleId(1L);

        Employee employee = new Employee();
        employee.setFirstname("S");
        employee.setSurname("Paul");

        Role role = new Role();
        role.setId(1L);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setName("S Paul");

        when(employeeMapper.toEntity(employeeRequest)).thenReturn(employee);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeService.createEmployee(employeeRequest);

        assertEquals("S Paul", result.getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("S");
        employee.setSurname("Paul");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1L);
        employeeResponse.setName("S Paul");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(employee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeService.getEmployeeById(1L);

        assertEquals("S Paul", result.getName());
    }

    @Test
    void testUpdateEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("S Paul");
        employeeRequest.setRoleId(1L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("S");
        employee.setSurname("Paul");

        Role role = new Role();
        role.setId(1L);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1L);
        employeeResponse.setName("S Paul");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeService.updateEmployee(1L, employeeRequest);

        assertEquals("S Paul", result.getName());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
