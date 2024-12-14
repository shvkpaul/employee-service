package com.shvkpaul.employee.service;

import com.shvkpaul.employee.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void deleteRoleAndReassignProjects(Long roleId, Long defaultEmployeeId) {
        roleRepository.deleteRoleAndReassignProjects(roleId, defaultEmployeeId);
    }

}
