package com.fadhil.basicspringboot.service;

import com.fadhil.basicspringboot.model.Student;
import com.fadhil.basicspringboot.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // CREATE New Student
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email already exist");
        }
        studentRepo.save(student);
    }

    // READ All Students or by Email
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // READ Student by ID
    public Optional<Student> getStudentById(Long studentId) {
        return studentRepo.findById(studentId);
    }

    // READ Student by Email
    public Iterable<Student> getStudentByEmail(String email) {
        return studentRepo.getByEmail(email);
    }

    // UPDATE Student
    @Transactional
    public void updateStudent(Long studentId, String first_name, String last_name) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
        if (first_name != null && first_name.length() > 0 && !Objects.equals(student.getFirst_name(), first_name)) {
            student.setFirst_name(first_name);
        }
        if (last_name != null && last_name.length() > 0 && !Objects.equals(student.getLast_name(), last_name)) {
            student.setLast_name(first_name);
        }
    }

    // DELETE Student
    public void deleteStudent(Long studentId) {
        boolean isStudentExist = studentRepo.existsById(studentId);
        if (!isStudentExist) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepo.deleteById(studentId);
    }
}
