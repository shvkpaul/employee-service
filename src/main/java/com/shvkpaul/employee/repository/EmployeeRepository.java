package com.shvkpaul.employee.repository;

import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByRole(Role role);
}
