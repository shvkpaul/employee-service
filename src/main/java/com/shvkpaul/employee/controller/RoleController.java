package com.shvkpaul.employee.controller;

import com.shvkpaul.employee.model.GenericResponse;
import com.shvkpaul.employee.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing role", description = "Delete an existing role in the system")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "successfully deleted the role",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            )
        }
    )
    public ResponseEntity<GenericResponse> deleteRole(
        @PathVariable Long id,
        @RequestParam Long defaultEmployeeId) {
        roleService.deleteRoleAndReassignProjects(id, defaultEmployeeId);
        return ResponseEntity.ok(new GenericResponse("Role and associated employees deleted successfully"));
    }
}
