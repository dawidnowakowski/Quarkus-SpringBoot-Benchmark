package pl.poznan.put.student.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentDTO(LocalDate enrollmentDate, BigDecimal grade, CourseDTO course) {
}