package pl.poznan.put.student.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
        @NotBlank(message = "title cannot be blank") String title,
        @NotBlank(message = "department cannot be blank") String department,
        @NotNull @Min(value = 0, message = "credits must not be lower than 0.0") @Max(value = 30, message = "credits must not exceed 30.0")
        Integer credits) {
}