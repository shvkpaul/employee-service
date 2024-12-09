package com.shvkpaul.employee.service;

import com.shvkpaul.employee.dto.EmployeeDTO;
import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Role;
import com.shvkpaul.employee.mapper.EmployeeMapper;
import com.shvkpaul.employee.repository.EmployeeRepository;
import com.shvkpaul.employee.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Role role = roleRepository.findById(employeeDTO.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        employee.setRole(role);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        Role role = roleRepository.findById(employeeDTO.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        employee.setFirstname(employeeDTO.getName().split(" ")[0]);
        employee.setSurname(employeeDTO.getName().split(" ")[1]);
        employee.setRole(role);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
