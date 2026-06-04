package pl.poznan.put.student.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.model.Student;
import pl.poznan.put.student.repository.StudentRepository;
import pl.poznan.put.student.utils.StudentMapper;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public List<StudentDTO> getAllStudents() {
        return studentRepository.fetchAll().stream().map(StudentMapper::map).toList();
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudent(Long id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return StudentMapper.map(student);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.map(studentDTO);
        student = this.studentRepository.save(student);
        return StudentMapper.map(student);
    }

    @Transactional
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = this.studentRepository.findById(studentDTO.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
