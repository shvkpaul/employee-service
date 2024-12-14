package com.shvkpaul.employee.service;

import com.shvkpaul.employee.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteRoleAndReassignProjects() {
        Long roleId = 1L;
        Long defaultEmployeeId = 2L;

        roleService.deleteRoleAndReassignProjects(roleId, defaultEmployeeId);

        verify(roleRepository).deleteRoleAndReassignProjects(roleId, defaultEmployeeId);
    }
}
