package com.shvkpaul.employee.mapper;


import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        if (employeeRequest.getName().contains(" ")) {
            employee.setFirstname(employeeRequest.getName().split(" ")[0]);
            employee.setSurname(employeeRequest.getName().split(" ")[1]);
        } else {
            employee.setFirstname(employeeRequest.getName());
            employee.setSurname("");
        }

        return employee;
    }


    public EmployeeResponse toDto(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setId(employee.getId());
        employeeResponse.setName(employee.getFirstname() + " " + employee.getSurname());
        employeeResponse.setRoleId(employee.getRole().getId());

        return employeeResponse;
    }
}
