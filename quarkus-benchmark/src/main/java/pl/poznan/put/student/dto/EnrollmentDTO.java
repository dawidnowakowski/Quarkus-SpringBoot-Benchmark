package pl.poznan.put.student.dto;

import java.time.LocalDate;

public record EnrollmentDTO(Long studentId, Long courseId, LocalDate enrollmentDate, Double grade) {
}