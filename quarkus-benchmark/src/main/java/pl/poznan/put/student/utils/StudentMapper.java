package pl.poznan.put.student.utils;

import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.model.Student;

public class StudentMapper {
    public static StudentDTO map(Student student) {
        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getEnrollments().stream().map(EnrollmentMapper::map).toList()
        );
    }
}
