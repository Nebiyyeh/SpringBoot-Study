package com.neb.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neb.domain.Student;
import com.neb.dto.StudentDTO;
import com.neb.exception.ConflictException;
import com.neb.exception.ResourceNotFound;
import com.neb.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentRepository stdrepo;
	
	@Override
	public List<Student> getAllStudents() {
		return stdrepo.findAll();
		
	}

	@Override
	public Student getStudentById(Long id) {
		return stdrepo.findById(id).orElseThrow(()->new ResourceNotFound("Student is not found with id: "+id));	
	}

	@Override
	public void saveStudent(StudentDTO studentDTO) {
   
		boolean exists=existsByEmail(studentDTO.getEmail());
		
		if(exists) {
			throw new ConflictException("This email already exists!");
		}
		
		Student student=new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setGrade(studentDTO.getGrade());
		student.setPhone(studentDTO.getPhone());
		student.setEmail(studentDTO.getEmail());
		
		stdrepo.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		Student foundStudent=getStudentById(id);
		stdrepo.delete(foundStudent);
		
	}

	@Override
	public void updateStudent(Long id, StudentDTO studentDTO) {
		Student foundStudent=getStudentById(id);
		
		boolean exists=existsByEmail(studentDTO.getEmail());
		
		if(exists&&!studentDTO.getEmail().equals(foundStudent.getEmail())) {
			throw new ConflictException("This email already exists!");
		}
		
		foundStudent.setFirstName(studentDTO.getFirstName());
		foundStudent.setLastName(studentDTO.getLastName());
		foundStudent.setGrade(studentDTO.getGrade());
		foundStudent.setPhone(studentDTO.getPhone());
		foundStudent.setEmail(studentDTO.getEmail());
		
		// email is defined unique, so should check before updating
		
		stdrepo.save(foundStudent);
		
	}

	@Override
	public boolean existsByEmail(String email) {
	
		return stdrepo.existsByEmail(email);
	}

}
