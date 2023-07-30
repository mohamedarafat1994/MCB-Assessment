package com.mcb.controllers;

import java.util.Collections;
import java.util.List;

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

import com.mcb.service.SubjectService;
import com.mcb.models.Subject;
import com.mcb.models.exception.NoDataFoundException;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

	@Autowired
	private SubjectService service;

	@PostMapping("/addSubject")
	public ResponseEntity<?> insertSubject(@RequestBody Subject subject) {
		Subject createdEntity = service.save(subject);
		return new ResponseEntity<>(Collections.singletonMap("Status", "subject created with id : " + createdEntity.getId()),HttpStatus.CREATED);
	}

	@GetMapping("/subjects")
	public ResponseEntity<List<Subject>> getAll() {
		return new ResponseEntity<List<Subject>>(service.getAll(), HttpStatus.OK);
	}

	@PutMapping("/{subjectId}")
	public ResponseEntity<?> updateSubject(@RequestBody Subject subject, @PathVariable("subjectId") Long subjectId) {
		Subject existingEntity = service.findById(subjectId).orElseThrow(() -> new NoDataFoundException());
		existingEntity.setTitle(subject.getTitle());
		return new ResponseEntity<Subject>(service.update(existingEntity), HttpStatus.OK);
	}

	@GetMapping("/{subjectId}")
	public ResponseEntity<?> getById(@PathVariable("subjectId") Long subjectId) {
		return new ResponseEntity<Subject>(service.findById(subjectId).orElseThrow(() -> new NoDataFoundException()), HttpStatus.OK);
	}

	@DeleteMapping("/{subjectId}")
	public ResponseEntity<?> delete(@PathVariable("subjectId") Long subjectId) {
		Subject s = service.findById(subjectId).orElseThrow(() -> new NoDataFoundException());
		service.delete(s);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
