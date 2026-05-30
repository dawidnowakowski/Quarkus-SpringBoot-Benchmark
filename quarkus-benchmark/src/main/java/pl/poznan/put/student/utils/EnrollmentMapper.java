package pl.poznan.put.student.utils;

import pl.poznan.put.student.dto.EnrollmentDTO;
import pl.poznan.put.student.model.Enrollment;

public class EnrollmentMapper {
    public static EnrollmentDTO map(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getEnrollmentDate(),
                enrollment.getGrade(),
                CourseMapper.map(enrollment.getCourse())
        );
    }
}
