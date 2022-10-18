package com.neb.service;

import java.util.List;

import com.neb.domain.Student;

public interface StudentService {

	List<Student> getAllStudents();
	Student getStudentById(Long id);
	void saveStudent(Student student);
	void deleteStudent(Long id);
	void updateStudent (Long id,Student student);
	
}
