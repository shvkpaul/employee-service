package com.shvkpaul.employee.controller;

import com.shvkpaul.employee.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
class RoleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    void testDeleteRole() throws Exception {
        Long roleId = 1L;
        Long defaultEmployeeId = 2L;

        doNothing().when(roleService).deleteRoleAndReassignProjects(roleId, defaultEmployeeId);

        mockMvc.perform(delete("/api/roles/{id}", roleId)
                .param("defaultEmployeeId", defaultEmployeeId.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"message\":\"Role and associated employees deleted successfully\"}"));
    }
}
