package com.mcb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Marks;
import com.mcb.models.Student;
import com.mcb.payload.response.StudentSubjects;
import com.mcb.repository.MarksRepository;

@Service
public class MarksService {

	@Autowired
	private MarksRepository repo;
	
	public Marks save(Marks mark) {
		return repo.save(mark);
	}
	
	public List<Marks> getAll(){
		return repo.findAll();
	}
	
	public Optional<Marks> findById(Long markId) {
		return repo.findById(markId);
	}
	
	public void remove(Long markId) {
		repo.deleteById(markId);
	}
	
	public Marks update(Marks mark, Marks existingEntity) {
		if(mark.getDate() != null)
			existingEntity.setDate(mark.getDate());
		if(mark.getMark() != 0 || mark.getMark() != existingEntity.getMark())
			existingEntity.setMark(mark.getMark());
		if(mark.getStudent() != null)
			existingEntity.setStudent(mark.getStudent());
		if(mark.getSubject() != null)
			existingEntity.setSubject(mark.getSubject());
		return repo.save(existingEntity);
	}

	public List<Marks> getMarkByStudent(Student student) {
		return repo.findByStudent(student);
	}

	public List<StudentSubjects> getAllMarksByStudentId(Long studentId) {
		List<Marks> list = repo.getAllMarksByStudentId(studentId);
		Map<String,List<Marks>> response = new HashMap<>();
		for(Marks mark : list) {
			String subjectTitle = mark.getSubject().getTitle();
			List<Marks> tempList;
			if(response.containsKey(subjectTitle)) {
				tempList = response.get(subjectTitle);
				
			}else {
				tempList =  new ArrayList<Marks>();
			}
			mark.setStudent(null);
			mark.setSubject(null);
			tempList.add(mark);
			response.put(subjectTitle, tempList);
			
		}
		List<StudentSubjects> responsePayload = new ArrayList<>();
		for(Entry<String, List<Marks>> entry : response.entrySet()) {
			responsePayload.add(new StudentSubjects(entry.getKey(),entry.getValue()));
		}
		return responsePayload;
	}
}
