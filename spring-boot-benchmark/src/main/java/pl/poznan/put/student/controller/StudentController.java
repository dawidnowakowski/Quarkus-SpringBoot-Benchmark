package pl.poznan.put.student.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return this.studentService.getStudent(id);
    }

    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO newStudent) {
        return this.studentService.createStudent(newStudent);
    }

    @PutMapping
    public StudentDTO updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return this.studentService.updateStudent(studentDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
    }
}
