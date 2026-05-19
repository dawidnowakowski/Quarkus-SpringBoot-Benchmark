package pl.poznan.put.student.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pl.poznan.put.student.model.Student;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
}
