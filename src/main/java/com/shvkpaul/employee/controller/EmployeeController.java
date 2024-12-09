package com.shvkpaul.employee.controller;


import com.shvkpaul.employee.model.EmployeeRequest;
import com.shvkpaul.employee.model.EmployeeResponse;
import com.shvkpaul.employee.model.GenericResponse;
import com.shvkpaul.employee.service.EmployeeService;
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
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    @Operation(summary = "Create a new employee", description = "Creates a new employee in the system")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "201", description = "successfully created a employee",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            )
        }
    )
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an existing employee", description = "Find an existing employee in the system")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "successfully fetched the employee",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            )
        }
    )
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee", description = "Updates an existing employee in the system")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "successfully updated the employee",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            )
        }
    )
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing employee", description = "Delete an existing employee in the system")
    @ApiResponses(value =
        {
            @ApiResponse(responseCode = "200", description = "successfully deleted the employee",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            )
        }
    )
    public ResponseEntity<GenericResponse> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new GenericResponse("Employee deleted successfully"));
    }
}
