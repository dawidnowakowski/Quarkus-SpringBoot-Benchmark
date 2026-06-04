package pl.poznan.put.student.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentDTO(
        @NotBlank(message = "enrollmentDate cannot be blank")
        LocalDate enrollmentDate,

        @NotNull(message = "grade cannot be null") @Min(value = 0, message = "The grade must not be lower than 0.0") @Max(value = 100, message = "The grade must not exceed 100.0")
        BigDecimal grade,

        @NotNull
        CourseDTO course) {
}