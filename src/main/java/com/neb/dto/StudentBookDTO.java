package com.neb.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentBookDTO {

	private Long id;
	private String firstName;
	private String lastName;
	 
	private List<String> books;
	
}
