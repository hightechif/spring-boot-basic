package com.fadhil.basicspringboot.service;

import com.fadhil.basicspringboot.dto.ResponseDTO;
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
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        return studentDTO;
    }

    // CREATE New Student
    public StudentDTO addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email already exist");
        }
        Student result = studentRepo.save(student);
        return convertToDTO(result);
    }

    // READ All Students
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // READ Student by ID
    public Optional<StudentDTO> getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new IllegalStateException("student with id "+ studentId +" does not exist");
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentOptional.get().getId());
        studentDTO.setFirstName(studentOptional.get().getFirstName());
        studentDTO.setLastName(studentOptional.get().getLastName());
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
    public void updateStudent(Long studentId, String firstName, String lastName) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }
    }

    // DELETE Student
    public ResponseDTO deleteStudent(Long studentId) {
        boolean isStudentExist = studentRepo.existsById(studentId);
        if (!isStudentExist) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepo.deleteById(studentId);
        return new ResponseDTO("Delete Success");
    }
}
