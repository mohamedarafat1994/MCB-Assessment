package com.mcb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Subject;
import com.mcb.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repo;

	public Subject save(Subject subject) {
		return repo.save(subject);
	}

	public Optional<Subject> findById(Long subjectId) {
		return repo.findById(subjectId);
	}

	public Subject update(Subject existingEntity) {
		return save(existingEntity);
	}

	public List<Subject> getAll() {
		return repo.findAll();
	}

	public void delete(Subject existingEntity) {
		repo.delete(existingEntity);
	}
	
}
