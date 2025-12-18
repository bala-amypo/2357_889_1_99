package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotaion.*;
import java.util.*;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;

@RestController;
public class StudentController{
    @Autowired
    StudentService src;
    @PostMapping("/post")
    public StudentEntity postdata(@RequestBody StudentEntity st){
        return src.savedata();
        
    }

}
