package com.neb.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neb.domain.Student;
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
	public void saveStudent(Student student) {
		stdrepo.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		Student foundStudent=getStudentById(id);
		stdrepo.delete(foundStudent);
		
	}

	@Override
	public void updateStudent(Long id, Student student) {
		Student foundStudent=getStudentById(id);
		foundStudent.setFName(student.getFName());
		foundStudent.setLName(student.getLName());
		foundStudent.setGrade(student.getGrade());
		foundStudent.setPhone(student.getPhone());
		
		//foundStudent.setEmail(student.getEmail());
		// we defined email unique, so should check before updating
		
		stdrepo.save(foundStudent);
		
		
	}

}
