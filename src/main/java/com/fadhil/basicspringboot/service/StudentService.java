package com.fadhil.basicspringboot.service;

import com.fadhil.basicspringboot.dto.StudentDTO;
import com.fadhil.basicspringboot.model.Student;
import com.fadhil.basicspringboot.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Convert to DTO;
    private StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirst_name(student.getFirst_name());
        studentDTO.setLast_name(student.getLast_name());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        return studentDTO;
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
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // READ Student by ID
    public Optional<StudentDTO> getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentOptional.get().getId());
        studentDTO.setFirst_name(studentOptional.get().getFirst_name());
        studentDTO.setLast_name(studentOptional.get().getLast_name());
        studentDTO.setEmail(studentOptional.get().getEmail());
        studentDTO.setAge(studentOptional.get().getAge());
        Optional<StudentDTO> studentDTOOptional = Optional.of(studentDTO);
        return studentDTOOptional;
    }

    // READ Student by Email
    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepo.getByEmail(email);
        return convertToDTO(student);
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
