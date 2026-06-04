package pl.poznan.put.student.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record StudentDTO(
        Long id,
        @NotBlank(message = "firstName cannot be blank") String firstName,
        @NotBlank(message = "lastName cannot be blank") String lastName,
        @NotBlank(message = "email cannot be blank") @Email String email,
        List<@Valid EnrollmentDTO> enrollments) {
}