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
import com.mcb.models.Group;
import com.mcb.service.GroupService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

	@Autowired
	private GroupService service;

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test_created_group() throws Exception {
		Group group = new Group(1L, "group1");

		mockMvc.perform(
				post("/api/group/addGroup")
				.contentType("application/json")
				.content(mapper.writeValueAsString(group)))
				.andExpect(status().isCreated());

		Group g = service.findById(1L).get();
		assertThat(g.getName()).isEqualTo("group1");
	}
	
	@Test
	public void getById() throws Exception {
		service.save(new Group(1L,"g1"));
		mockMvc.perform(
				get("/api/group/{groupId}", 1))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1));
				
	}
	
	
	@Test
	public void getAll() throws Exception {
		service.save(new Group(1L,"g1"));
		service.save(new Group(2L,"g2"));
		mockMvc.perform(
				get("/api/group/groups"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(2));
				
	}
}
