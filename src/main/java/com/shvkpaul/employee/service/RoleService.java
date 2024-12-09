package com.shvkpaul.employee.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final ApplicationContext applicationContext;

    public RoleService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void deleteRole(Long roleId) {
        RoleServiceUtility.deleteRole(applicationContext, roleId);
    }
}
