package com.shvkpaul.employee.mapper;


import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        String[] nameParts = employeeRequest.getName().trim().split(" ");

        if (nameParts.length > 1) {
            employee.setFirstname(nameParts[0]);
            employee.setSurname(nameParts[1]);
        } else {
            employee.setFirstname(nameParts[0]);
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
