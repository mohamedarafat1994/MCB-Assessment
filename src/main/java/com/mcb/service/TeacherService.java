package com.mcb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Group;
import com.mcb.models.Subject;
import com.mcb.models.Teacher;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository repo;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private SubjectService subjectService;

	public List<Teacher> getAll() {
		return repo.findAll();
	}

	public Teacher save(Teacher teacher) {
		groupService.findById(teacher.getGroup().getId()).orElseThrow(() -> new NoDataFoundException());
		subjectService.findById(teacher.getSubject().getId()).orElseThrow(() -> new NoDataFoundException());
		Teacher createdTeacher = repo.save(teacher);
		return createdTeacher;
	}

	public Optional<Teacher> findById(Long teacherId) {
		return repo.findById(teacherId);
	}

	public void remove(Long teacherId) {
		repo.deleteById(teacherId);
	}

	public Teacher update(Teacher newTeacher, Teacher existingTeacher) {
		if(newTeacher.getGroup() != null) {
			existingTeacher.setGroup(newTeacher.getGroup());
		}
		if(newTeacher.getSubject() != null) {
			existingTeacher.setSubject(newTeacher.getSubject());
		}
		return repo.save(existingTeacher);
	}
	
}
