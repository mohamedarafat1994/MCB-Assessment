package com.mcb.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.models.Group;
import com.mcb.models.Subject;
import com.mcb.models.Teacher;
import com.mcb.repository.GroupRepository;
import com.mcb.repository.SubjectRepository;
import com.mcb.service.TeacherService;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

	@Autowired
	private TeacherService service;

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	private SubjectRepository subRepo;
	
	@Test
	public void test_insert() throws JsonProcessingException, Exception {
		Group group = groupRepo.save(new Group(1L,"group")) ;
		Subject subject = subRepo.save(new Subject(1L,"subject"));
		Teacher t = new Teacher(1L,subject, group);
		
		mockMvc.perform(
				post("/api/teacher/addTeacher")
				.contentType("application/json")
				.content(mapper.writeValueAsString(t)))
				.andExpect(status().isCreated());
		
	}
	
}
