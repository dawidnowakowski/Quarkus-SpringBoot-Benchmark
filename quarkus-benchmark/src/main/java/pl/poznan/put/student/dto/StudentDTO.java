package pl.poznan.put.student.dto;

import java.util.List;

public record StudentDTO(Long id, String firstName, String lastName, String email, List<EnrollmentDTO> enrollments) {
}