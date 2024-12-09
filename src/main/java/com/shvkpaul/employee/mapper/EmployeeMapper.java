package com.shvkpaul.employee.mapper;

import com.shvkpaul.employee.dto.EmployeeDTO;
import com.shvkpaul.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "role.id", target = "roleId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "roleId", target = "role.id")
    Employee toEntity(EmployeeDTO employeeDTO);
}
