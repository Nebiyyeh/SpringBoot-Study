package com.neb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.neb.domain.Book;
import com.neb.domain.Student;
import com.neb.dto.StudentBookDTO;
import com.neb.dto.StudentDTO;

@Mapper (componentModel = "spring")
public interface StudentMapper {
	
	
	List<StudentDTO> mapToStdDTO(List<Student> studentList);
	StudentDTO stdToStdDTO(Student student);
	
	
	List<StudentBookDTO>  mapToStudentBookDTO(List<Student> stdList);
	
	@Mapping(source = "books", target="books", qualifiedByName="getBookNames")
	StudentBookDTO studentToStudentBookDTO(Student student);
	
	@Named("getBookNames")
	public static List<String> getNames(List<Book> books){
		List<String> bookList= new ArrayList<>();
		bookList=books.stream().map(b->b.getName()).collect(Collectors.toList());
		return bookList;
	}
	
	

}
