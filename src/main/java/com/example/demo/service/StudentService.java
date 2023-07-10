package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    //CREATE
    public Student createStudent (Student stu){
        return studentRepository.save(stu);
    }
    //READ
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }
    //DELETE
    public void deleteStudent(Long student_id){
        studentRepository.deleteById(student_id);
    }
    //UPDATE
    public Student updateStudent(Long studentId , Student studentDetails){
        Student stu = studentRepository.findById(studentId).get();
        stu.setRollNo(studentDetails.getRollNo());
        stu.setName(studentDetails.getName());

        return studentRepository.save(stu);
    }
}
