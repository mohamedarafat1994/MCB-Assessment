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

import com.mcb.models.Teacher;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.service.TeacherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	@Autowired
	private TeacherService service;	
	
	@PostMapping("/addTeacher")
	public ResponseEntity<?> insertSubject(@RequestBody Teacher teacher) {
		Teacher createdEntity = service.save(teacher);
		return new ResponseEntity<>(Collections.singletonMap("Status", "Teacher created with id : " + createdEntity.getId()),HttpStatus.CREATED);
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<Teacher>> getAll(){
		return new ResponseEntity<List<Teacher>>(service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<?> getTeacherById(@PathVariable("teacherId") Long teacherId){ 
		return new ResponseEntity<Teacher>(service.findById(teacherId).orElseThrow(() -> new NoDataFoundException()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<?> deleteById(@PathVariable("teacherId") Long teacherId ){
		service.findById(teacherId).orElseThrow(() -> new NoDataFoundException());
		service.remove(teacherId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{teacherId}")
	public ResponseEntity<?> update(@PathVariable("teacherId") Long teacherId,@RequestBody Teacher teacher){
		Teacher existingEntity = service.findById(teacherId).orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<Teacher>(service.update(teacher,existingEntity), HttpStatus.OK);
	}
}
