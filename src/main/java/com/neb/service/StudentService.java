package com.neb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neb.domain.Student;
import com.neb.dto.StudentBookDTO;
import com.neb.dto.StudentDTO;

public interface StudentService {

	List<Student> getAllStudents();
	Student getStudentById(Long id);
	void saveStudent(StudentDTO studentDTO);
	void deleteStudent(Long id);
	void updateStudent (Long id, StudentDTO studentDTO);
	boolean existsByEmail(String email);
	Page<Student> getStudentPage(Pageable pageable);
	
	List<Student> findAllEqualsGrade(Integer grade);
	
	StudentDTO findStudentDTOById(Long id);
	
	List<Student> getStudents();
	
	List<StudentDTO> getStudentDTOs();
	
	List<StudentBookDTO> getStudentBookDTO();
	Page<StudentBookDTO> getstdBookDTOPage(Pageable pageable);
	
	
}
