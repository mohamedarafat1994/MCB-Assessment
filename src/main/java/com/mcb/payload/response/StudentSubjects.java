package com.mcb.payload.response;

import java.util.List;

import com.mcb.models.Marks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentSubjects {

	private String title;
	private List<Marks> marks;
	
}
