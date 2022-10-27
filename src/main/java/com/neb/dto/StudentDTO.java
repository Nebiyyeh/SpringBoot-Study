package com.neb.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.neb.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {


	@NotBlank (message = "Provide first name")
	@Size (min=4, max=100, message="First name '${validatedValue}' should be between {min} and {max}")
	
	private String firstName;
	
	@NotBlank (message = "Provide last name")
	@Size (min=4, max=100, message="First name '${validatedValue}' should be between {min} and {max}")
	
	private String lastName;
	
	@NotNull(message = "Please provide grade")
	private Integer grade;
	
	@Pattern(regexp= "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
	private String phone;
	
	@Email(message = "Please provide a valid email")  
	private String email;
	
	public StudentDTO(Student student) {
		this.firstName=student.getFirstName();
		this.lastName=student.getLastName();
		this.grade=student.getGrade();
		this.phone=student.getPhone();
		this.email=student.getEmail();
	}
	

}
