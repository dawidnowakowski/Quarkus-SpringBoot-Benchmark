package pl.poznan.put.student.service;

import org.springframework.stereotype.Service;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.model.Student;
import pl.poznan.put.student.repository.StudentRepository;
import pl.poznan.put.student.utils.StudentMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.fetchAll().stream().map(StudentMapper::map).toList();
    }

    public StudentDTO getStudent(Long id) {
        Optional<Student> student = this.studentRepository.findById(id);
        return student.map(StudentMapper::map).orElse(null);
    }
}
