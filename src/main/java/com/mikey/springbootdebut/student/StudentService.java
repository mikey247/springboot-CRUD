package com.mikey.springbootdebut.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void registerNewStudent(Student student) {
        Student studentByEmail =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail != null){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "Student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found with ID: " + studentId));

        // Check if the email is already used by another student
        Student existingStudentWithEmail = studentRepository.findStudentByEmail(updatedStudent.getEmail());
        if (existingStudentWithEmail != null && !existingStudentWithEmail.getId().equals(studentId)) {
            throw new IllegalStateException("Email is already used by another student.");
        }

        // Update the properties of the retrieved student entity if the corresponding values are not null
        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }
        if (updatedStudent.getEmail() != null) {
            student.setEmail(updatedStudent.getEmail());
        }
        if (updatedStudent.getDob() != null) {
            student.setDob(updatedStudent.getDob());
        }
    }
}
