package com.shvkpaul.employee.repository;

import com.shvkpaul.employee.entity.Employee;
import com.shvkpaul.employee.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByEmployee(Employee employee);
}
