package pl.poznan.put.student.utils;

import pl.poznan.put.student.dto.CourseDTO;
import pl.poznan.put.student.model.Course;

public class CourseMapper {
    public static CourseDTO map(Course course) {
        return new CourseDTO(
                course.getTitle(),
                course.getDepartment(),
                course.getCredits()
        );
    }
}
