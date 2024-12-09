package com.shvkpaul.employee.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Role ID is mandatory")
    private String role_id;
}
