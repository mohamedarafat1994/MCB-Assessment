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

import com.mcb.models.Marks;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.service.MarksService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/marks")
public class MarksController {

	
	@Autowired
	private MarksService service;
	
	@PostMapping("/addMark")
	public ResponseEntity<?> insertSubject(@RequestBody Marks mark) {
		Marks createdEntity = service.save(mark);
		return new ResponseEntity<>(Collections.singletonMap("Status", "Mark created with id : " + createdEntity.getId()),HttpStatus.CREATED);
	}
	
	@GetMapping("/marks")
	public ResponseEntity<List<Marks>> getAll(){
		return new ResponseEntity<List<Marks>>(service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{markId}")
	public ResponseEntity<?> getById(@PathVariable("markId") Long markId){
		Marks mark = service.findById(markId).orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<Marks>(mark, HttpStatus.OK);
	}
	
	@DeleteMapping("/{markId}")
	public ResponseEntity<?> deleteById(@PathVariable("markId") Long markId ){
		service.findById(markId).orElseThrow(() -> new NoDataFoundException());
		service.remove(markId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{markId}")
	public ResponseEntity<?> update(@PathVariable("markId") Long markId,@RequestBody Marks mark){
		Marks existingEntity = service.findById(markId).orElseThrow(() -> new NoDataFoundException());;
		return new ResponseEntity<Marks>(service.update(mark,existingEntity), HttpStatus.OK);
	}
}
