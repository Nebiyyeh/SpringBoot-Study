package com.neb.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank (message = "Provide first name")
	@Size (min=4, max=100, message="First name '${validatedValue}' should be between {min} and {max}")
	@Column(length = 100 ,nullable = false)
	private String firstName;
	
	@NotBlank (message = "Provide last name")
	@Size (min=4, max=100, message="First name '${validatedValue}' should be between {min} and {max}")
	@Column(length = 100 ,nullable = false)
	private String lastName;
	
	@NotNull(message = "Please provide grade")
	private Integer grade;
	
	@Column(length = 14)
	@Pattern(regexp= "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
	private String phone;
	
	@Column(length = 100, nullable = false, unique = true)  //constraint for db
	@Email(message = "Please provide a valid email")  //validation
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
	@Setter(AccessLevel.NONE)
	private LocalDateTime createdAt=LocalDateTime.now();
	
	 @OneToMany(mappedBy = "student")
	 private List<Book> books;
	
}
