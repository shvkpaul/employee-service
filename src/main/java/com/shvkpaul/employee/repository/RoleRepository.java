package com.shvkpaul.employee.repository;

import com.shvkpaul.employee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Procedure("DeleteRoleAndReassignProjects")
    void deleteRole(Long roleId, Long defaultEmployeeId);
}
