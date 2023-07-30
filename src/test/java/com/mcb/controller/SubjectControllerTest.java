package com.mcb.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.models.Subject;
import com.mcb.service.SubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

	
	@Autowired
	private SubjectService service;

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test_created_subject() throws Exception {
		Subject subject = new Subject(1L, "title1");

		mockMvc.perform(
				post("/api/subject/addSubject")
				.contentType("application/json")
				.content(mapper.writeValueAsString(subject)))
				.andExpect(status().isCreated());

		Subject s = service.findById(1L).get();
		assertThat(s.getTitle()).isEqualTo("title1");
	}
	
	@Test
	public void getById() throws Exception {
		service.save(new Subject(1L,"t1"));
		mockMvc.perform(
				get("/api/subject/{subjectId}", 1))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1));
				
	}
	
	
	@Test
	public void getAll() throws Exception {
		service.save(new Subject(1L,"title1"));
		service.save(new Subject(2L,"title2"));
		mockMvc.perform(
				get("/api/subject/subjects"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(2));
				
	}
	
}
