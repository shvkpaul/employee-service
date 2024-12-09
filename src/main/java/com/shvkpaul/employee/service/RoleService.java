package com.shvkpaul.employee.service;

import com.shvkpaul.employee.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void deleteRole(Long roleId, Long defaultEmployeeId) {
        roleRepository.deleteRole(roleId, defaultEmployeeId);
    }

}
