package com.neb.service;

import java.util.List;

import com.neb.domain.Student;
import com.neb.dto.StudentDTO;

public interface StudentService {

	List<Student> getAllStudents();
	Student getStudentById(Long id);
	void saveStudent(StudentDTO studentDTO);
	void deleteStudent(Long id);
	void updateStudent (Long id, StudentDTO studentDTO);
	boolean existsByEmail(String email);
	
	
}
