package pl.poznan.put.student.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestPath;
import pl.poznan.put.student.dto.StudentDTO;
import pl.poznan.put.student.service.StudentService;

import java.util.List;

@Path("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    public List<StudentDTO> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @POST
    public StudentDTO createStudent(@Valid StudentDTO newStudent) {
        return this.studentService.createStudent(newStudent);
    }

    @GET
    @Path("/{id}")
    public StudentDTO getStudentById(@RestPath Long id) {
        return this.studentService.getStudent(id);
    }

    @PUT
    public StudentDTO updateStudent(@Valid StudentDTO studentDTO) {
        return this.studentService.updateStudent(studentDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteStudent(@RestPath Long id) {
        this.studentService.deleteStudent(id);
    }
}
