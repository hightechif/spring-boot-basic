package com.fadhil.basicspringboot.controller;

import com.fadhil.basicspringboot.dto.ResponseDTO;
import com.fadhil.basicspringboot.dto.StudentDTO;
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
    public StudentDTO addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @GetMapping(path = "/get-all")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/get/{studentId}")
    public Optional<StudentDTO> getStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping(path = "/get-by-email")
    public StudentDTO getStudentByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @PutMapping(path = "/edit/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        studentService.updateStudent(studentId, firstName, lastName);
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public ResponseDTO deleteStudent(@PathVariable("studentId") Long studentId) {
        return studentService.deleteStudent(studentId);
    }
}
