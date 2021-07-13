package com.fadhil.basicspringboot.controller;

import com.fadhil.basicspringboot.model.Student;
import com.fadhil.basicspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/create-new")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @GetMapping(path = "/get-all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/get/{studentId}")
    public Optional<Student> getStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping(path = "/get-by-email")
    public Iterable<Student> getStudentByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @PutMapping(path = "/edit/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String first_name,
                              @RequestParam(required = false) String last_name) {
        studentService.updateStudent(studentId, first_name, last_name);
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
