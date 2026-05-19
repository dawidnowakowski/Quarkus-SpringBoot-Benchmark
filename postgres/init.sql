CREATE TABLE students
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE courses
(
    id         BIGINT       NOT NULL,
    title      VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255) NOT NULL,
    credits    INT          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE enrollments
(
    student_id      BIGINT NOT NULL,
    course_id       BIGINT NOT NULL,
    enrollment_date DATE   NOT NULL,
    grade           NUMERIC(4, 2),
    PRIMARY KEY (student_id, course_id)
);

ALTER TABLE IF EXISTS enrollments
    ADD CONSTRAINT student_id_fk
    FOREIGN KEY (student_id) REFERENCES students;

ALTER TABLE IF EXISTS enrollments
    ADD CONSTRAINT course_id_fk
    FOREIGN KEY (course_id) REFERENCES courses;

CREATE SEQUENCE students_seq START WITH 16 INCREMENT BY 1;
CREATE SEQUENCE courses_seq START WITH 6 INCREMENT BY 1;

INSERT INTO students (id, first_name, last_name, email)
VALUES (1, 'Dawid', 'Nowakowski', 'dawid.nowakowski@student.put.poznan.pl'),
       (2, 'Bob', 'Jones', 'bob.jones@student.put.poznan.pl'),
       (3, 'Charlie', 'Brown', 'charlie.brown@student.put.poznan.pl'),
       (4, 'Diana', 'Prince', 'diana.prince@student.put.poznan.pl'),
       (5, 'Evan', 'Wright', 'evan.wright@student.put.poznan.pl'),
       (6, 'Alice', 'Smith', 'alice.smith@student.put.poznan.pl'),
       (7, 'Frank', 'Miller', 'frank.miller@student.put.poznan.pl'),
       (8, 'Grace', 'Hopper', 'grace.hopper@student.put.poznan.pl'),
       (9, 'Henry', 'Ford', 'henry.ford@student.put.poznan.pl'),
       (10, 'Ivy', 'Chen', 'ivy.chen@student.put.poznan.pl'),
       (11, 'Jack', 'Sparrow', 'jack.sparrow@student.put.poznan.pl'),
       (12, 'Karen', 'Page', 'karen.page@student.put.poznan.pl'),
       (13, 'Leo', 'Tolstoy', 'leo.tolstoy@student.put.poznan.pl'),
       (14, 'Mia', 'Wallace', 'mia.wallace@student.put.poznan.pl'),
       (15, 'Nathan', 'Drake', 'nathan.drake@student.put.poznan.pl');

INSERT INTO courses (id, title, department, credits)
VALUES (1, 'Introduction to Computer Science', 'Engineering', 4),
       (2, 'Data Structures and Algorithms', 'Engineering', 4),
       (3, 'Calculus I', 'Mathematics', 3),
       (4, 'Physics for Engineers', 'Physics', 4),
       (5, 'World History', 'Arts', 3);

INSERT INTO enrollments (student_id, course_id, enrollment_date, grade)
VALUES
    (1, 1, '2025-09-01', 95.50),
    (1, 3, '2025-09-01', 88.00),
    (2, 1, '2025-09-02', 76.50),
    (2, 2, '2025-01-15', 91.00),
    (3, 4, '2025-09-01', 85.00),
    (4, 5, '2025-09-05', 98.00),
    (5, 1, '2025-09-01', 82.00),
    (5, 2, '2025-01-15', 89.50),
    (5, 3, '2025-09-01', 77.00),
    (6, 2, '2025-09-02', 88.00),
    (6, 4, '2025-09-03', 92.50),
    (7, 1, '2025-09-01', 70.00),
    (7, 3, '2025-09-04', 75.50),
    (8, 5, '2025-09-05', 100.00),
    (9, 2, '2025-09-02', 84.00),
    (9, 3, '2025-09-02', 81.50),
    (10, 4, '2025-09-01', 90.00),
    (11, 1, '2025-01-15', 88.00),
    (11, 2, '2025-01-16', 79.00),
    (11, 5, '2025-01-18', 95.00),
    (12, 3, '2025-09-01', 65.50),
    (13, 1, '2025-09-01', 72.00),
    (13, 4, '2025-09-04', 85.00),
    (14, 2, '2025-01-15', 93.00),
    (15, 3, '2025-09-01', 87.50),
    (15, 5, '2025-09-05', 91.00);