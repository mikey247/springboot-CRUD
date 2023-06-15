package com.mikey.springbootdebut.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
      return studentService.getStudents();
    }
    @PostMapping("/register")
    public void registerNewStudent(@RequestBody Student student){
        studentService.registerNewStudent(student);
    }
    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody Student updatedStudent){
        studentService.updateStudent(studentId,updatedStudent);
    }
}