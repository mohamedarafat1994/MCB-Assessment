package com.mcb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Group;
import com.mcb.models.Student;
import com.mcb.repository.StudentRepository;
import com.mcb.repository.TeacherRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	public Student save(Student student) {
		return repo.save(student);
	}

	public List<Student> getAll() {
		return repo.findAll();
	}

	public Optional<Student> findById(Long studentId) {
		return repo.findById(studentId);
	}

	public void remove(Long studentId) {
		repo.deleteById(studentId);
	}

	public Student update(Student student, Student existingEntity) {
		if(student.getFirstName()!= null)
			existingEntity.setFirstName(student.getFirstName());
		if(student.getLastName() != null)
			existingEntity.setLastName(student.getLastName());
		return repo.save(existingEntity);
	}

	public List<Student> getAllStudentByGroup(Group group) {
		return repo.findByGroup(group);
	}
	
}
