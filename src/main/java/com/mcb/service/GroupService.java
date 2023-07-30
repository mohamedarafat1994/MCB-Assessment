package com.mcb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Group;
import com.mcb.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repo;

	public Group save(Group group) {
		return repo.save(group);
	}

	public Optional<Group> findById(Long groupId) {
		return repo.findById(groupId);
	}

	public Group update(Group existingEntity) {
		return save(existingEntity);
	}

	public List<Group> getAll() {
		return repo.findAll();
	}

	public void delete(Group existingEntity) {
		repo.delete(existingEntity);
	}
	
}
