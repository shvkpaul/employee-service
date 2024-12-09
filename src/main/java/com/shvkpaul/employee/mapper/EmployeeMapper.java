package com.shvkpaul.employee.mapper;

import com.shvkpaul.employee.dto.EmployeeDTO;
import com.shvkpaul.employee.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstname(employeeDTO.getName().split(" ")[0]);
        employee.setSurname(employeeDTO.getName().split(" ")[1]);
        return employee;
    }


    public EmployeeDTO toDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getFirstname() + " " + employee.getSurname());
        return employeeDTO;
    }
}
