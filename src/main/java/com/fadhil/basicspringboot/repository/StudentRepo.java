package com.fadhil.basicspringboot.repository;

import com.fadhil.basicspringboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    // SELECT * FROM student WHERE email = ?
    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    Iterable<Student> getByEmail(String email);

}
