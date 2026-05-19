package pl.poznan.put.student.service;

import jakarta.enterprise.context.ApplicationScoped;
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
        return studentRepository.listAll()
                .stream()
                .map(student -> new StudentDTO(
                        student.getFirstName(), student.getLastName(), student.getEmail()
                )).toList();
    }
}
