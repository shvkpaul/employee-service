package com.shvkpaul.employee.service;

import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Project;
import com.shvkpaul.employee.entity.Role;
import com.shvkpaul.employee.repository.EmployeeRepository;
import com.shvkpaul.employee.repository.ProjectRepository;
import com.shvkpaul.employee.repository.RoleRepository;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RoleServiceUtility {

    public static void deleteRole(ApplicationContext applicationContext, Long roleId) {
        RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
        EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);
        ProjectRepository projectRepository = applicationContext.getBean(ProjectRepository.class);

        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role not found"));

        List<Employee> employees = employeeRepository.findAllByRole(role);

        Employee defaultEmployee = employeeRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Default employee not found"));

        for (Employee employee : employees) {
            List<Project> projects = projectRepository.findAllByEmployee(employee);
            for (Project project : projects) {
                project.setEmployee(defaultEmployee);
                projectRepository.save(project);
            }
        }

        employeeRepository.deleteAll(employees);
        roleRepository.delete(role);
    }
}
