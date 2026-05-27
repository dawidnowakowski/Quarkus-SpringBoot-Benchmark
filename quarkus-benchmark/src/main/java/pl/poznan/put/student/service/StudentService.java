package pl.poznan.put.student.service;

import jakarta.enterprise.context.ApplicationScoped;
import pl.poznan.put.student.dto.CourseDTO;
import pl.poznan.put.student.dto.EnrollmentDTO;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.repository.StudentRepository;

import java.util.List;

@ApplicationScoped
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.listAll().stream().map(student -> new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getEnrollments().stream().map(enrollment -> new EnrollmentDTO(enrollment.getEnrollmentDate(), enrollment.getGrade(), new CourseDTO(enrollment.getCourse().getTitle(), enrollment.getCourse().getDepartment(), enrollment.getCourse().getCredits()))).toList())).toList();
    }

//    @Transactional
//    public StudentDTO createStudent(StudentDTO student) {
//        Student newStudent = new Student();
//        newStudent.setEmail(student.email());
//        newStudent.setFirstName(student.firstName());
//        newStudent.setLastName(student.lastName());
//        this.studentRepository.persist(newStudent);
//        return new StudentDTO(newStudent.getId(), newStudent.getFirstName(), newStudent.getLastName(), newStudent.getEmail(), newStudent.getEnrollments().stream().map(enrollment -> new EnrollmentDTO(enrollment.getEnrollmentDate(), enrollment.getGrade(), enrollment.getCourse())).toList());
//    }
}
