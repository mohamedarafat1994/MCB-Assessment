package com.mcb.controllers;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.models.Student;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/student")
public class StudentController {

	
Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	@Autowired
	private StudentService service;	
	
	@PostMapping("/addStudent")
	public ResponseEntity<?> insertSubject(@RequestBody Student student) {
		Student createdEntity = service.save(student);
		return new ResponseEntity<>(Collections.singletonMap("Status", "Student created with id : " + createdEntity.getId()),HttpStatus.CREATED);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAll(){
		return new ResponseEntity<List<Student>>(service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<?> getById(@PathVariable("studentId") Long studentId){
		Student student = service.findById(studentId).orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<?> deleteById(@PathVariable("studentId") Long studentId ){
		service.findById(studentId).orElseThrow(() -> new NoDataFoundException());
		service.remove(studentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> update(@PathVariable("studentId") Long studentId,@RequestBody Student student){
		Student existingEntity = service.findById(studentId).orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<Student>(service.update(student,existingEntity), HttpStatus.OK);
	}
}
