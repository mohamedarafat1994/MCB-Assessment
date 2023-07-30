package com.mcb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.models.Marks;
import com.mcb.models.Student;
import com.mcb.models.Subject;
import com.mcb.models.Teacher;
import com.mcb.models.exception.NoDataFoundException;
import com.mcb.payload.response.StudentSubjects;

@Service
public class TestService {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MarksService markService;
	
	@Autowired
	private TeacherService teacherService;
	
	public List<Marks> getMarkByStudentId(Long studentId){
		Student student = studentService.findById(studentId).orElseThrow(() -> new NoDataFoundException());
		List<Marks> marks = markService.getMarkByStudent(student);
		List<Marks> updatedList = new ArrayList<Marks>();
		for(Marks m : marks) {
			Marks temp = m;
			temp.setSubject(null);
			temp.setStudent(null);
			updatedList.add(temp);
		}
		return updatedList;
	}

	public List<Student> getStudentsByTeacherId(Long teacherId) {
		Teacher teacher = teacherService.findById(teacherId).orElseThrow(() -> new NoDataFoundException());
		return studentService.getAllStudentByGroup(teacher.getGroup());
	}
	
	public List<StudentSubjects> getAllSubjectMarkByStudentId(Long studentId){
		return markService.getAllMarksByStudentId(studentId);
	}
	
}
