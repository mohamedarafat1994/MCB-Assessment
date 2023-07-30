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

import com.mcb.models.Group;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.service.GroupService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/group")
public class GroupController {

	
	@Autowired
	private GroupService service;
	
	@PostMapping("/addGroup")
	public ResponseEntity<?> insertGroup(@RequestBody Group group){
		Group createdEntity = service.save(group);
		return new ResponseEntity<>(Collections.singletonMap("Status", "Group created with id : " + createdEntity.getId()),HttpStatus.CREATED);
	}
	
	@PutMapping("/{groupId}")
	public ResponseEntity<?> updateGroup(@RequestBody Group group, @PathVariable("groupId") Long groupId){
		Group existingEntity = service.findById(groupId).orElseThrow(() -> new NoDataFoundException());
		existingEntity.setName(group.getName());
		return new ResponseEntity<Group>(service.update(existingEntity), HttpStatus.OK);
	}
	
	@GetMapping("/groups")
	public ResponseEntity<List<Group>> getAll(){
		return new ResponseEntity<List<Group>>(service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{groupId}")
	public ResponseEntity<?> getById(@PathVariable("groupId") Long groupId){
		Group existingEntity = service.findById(groupId).orElseThrow(() -> new NoDataFoundException());
		return new ResponseEntity<Group>(existingEntity, HttpStatus.OK);
	}
	
	@DeleteMapping("/{groupId}")
	public ResponseEntity<?> delete(@PathVariable("groupId") Long groupId){
		Group existingEntity = service.findById(groupId).orElseThrow(() -> new NoDataFoundException());
		service.delete(existingEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
