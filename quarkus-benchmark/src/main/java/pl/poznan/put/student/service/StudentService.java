package pl.poznan.put.student.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.repository.StudentRepository;
import pl.poznan.put.student.utils.StudentMapper;

import java.util.List;

@ApplicationScoped
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<StudentDTO> getAllStudents() {
        return studentRepository.listAll().stream().map(StudentMapper::map).toList();
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
