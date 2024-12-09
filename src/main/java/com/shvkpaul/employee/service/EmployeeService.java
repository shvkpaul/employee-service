package com.shvkpaul.employee.service;


import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Role;
import com.shvkpaul.employee.mapper.EmployeeMapper;
import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
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

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        Role role = roleRepository.findById(employeeRequest.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        employee.setRole(role);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        Role role = roleRepository.findById(employeeRequest.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        employee.setFirstname(employeeRequest.getName().split(" ")[0]);
        employee.setSurname(employeeRequest.getName().split(" ")[1]);
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
