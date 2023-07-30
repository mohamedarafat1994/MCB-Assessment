package com.mcb.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.models.Marks;
import com.mcb.models.Student;
import com.mcb.service.TestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private TestService testService;
  
	@GetMapping("/getMarkByStudentId/{studentId}")
	public ResponseEntity<?> getMarkByStudentId(@PathVariable("studentId") Long studentId){
		List<Marks> marks = testService.getMarkByStudentId(studentId);//.orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<List<Marks>>(marks,HttpStatus.OK);
	}
	
	@GetMapping("/getStudentByTeacherId/{teacherId}")
	public ResponseEntity<?> getStudentByTeacherId(@PathVariable("teacherId") Long teacherId){
		List<Student> students = testService.getStudentsByTeacherId(teacherId);
		return new ResponseEntity<>(Collections.singletonMap("studentCount", students.size()),HttpStatus.OK);
	}
	
	@GetMapping("/getStudentSubjectMarks/{studentId}")
	public ResponseEntity<?> getStudentSubjectMarks(@PathVariable("studentId") Long studentId){
		return new ResponseEntity<>(testService.getAllSubjectMarkByStudentId(studentId),HttpStatus.OK);
	}
	
}
