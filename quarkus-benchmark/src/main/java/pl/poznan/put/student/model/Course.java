package pl.poznan.put.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "courses")
@Cacheable
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_seq")
    @SequenceGenerator(name = "courses_seq", sequenceName = "courses_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    @NotBlank
    @NaturalId
    private String title;

    @Column(name = "department")
    @NotBlank
    private String department;

    @Column(name = "credits")
    @NotNull
    @Min(value = 0, message = "credits must not be lower than 0.0")
    @Max(value = 30, message = "credits must not exceed 30.0")
    private Integer credits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
