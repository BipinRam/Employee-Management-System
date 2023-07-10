package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(value = "/students")
    public List<Student> readStudent(){
        return studentService.getStudent();
    }
    @PostMapping (value="/students")
    public Student createStudent(@RequestBody Student stu) {
        return studentService.createStudent(stu);
    }
    @PutMapping(value = "/students")
    public Student readStudent(@PathVariable(value = "studentId") Long studentId, @RequestBody Student studentDetails) {
        return studentService.updateStudent(studentId , studentDetails);
    }
    @DeleteMapping(value="/students/{studentId}")
    public void deleteStudent(@PathVariable(value = "studentId") Long id) {
        studentService.deleteStudent(id);
    }

}
