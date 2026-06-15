package pl.poznan.put.student.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.model.Student;
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
        return studentRepository.fetchAll().stream().map(StudentMapper::map).toList();
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.map(studentDTO);
        this.studentRepository.persist(student);
        return StudentMapper.map(student);
    }

    @Transactional
    public StudentDTO getStudent(Long id) {
        return StudentMapper.map(this.studentRepository.findById(id));
    }

    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = this.studentRepository.findById(studentDTO.id());
        if (student == null) {
            throw new NotFoundException();
        }

        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setEmail(studentDTO.email());
        return StudentMapper.map(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }
}
