package com.neb.service;


import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neb.domain.Student;
import com.neb.dto.StudentBookDTO;
import com.neb.dto.StudentDTO;
import com.neb.exception.ConflictException;
import com.neb.exception.ResourceNotFound;
import com.neb.mapper.StudentMapper;
import com.neb.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentRepository stdrepo;
	
	@Autowired
	private StudentMapper mapper;
	
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
//
	@Override
	public Page<Student> getStudentPage(Pageable pageable) {
		
		return stdrepo.findAll(pageable);
	}

	@Override
	public List<Student> findAllEqualsGrade(Integer grade) {
		return stdrepo.findAllEqualsGrade(grade);	
	}

	@Override
	public StudentDTO findStudentDTOById(Long id) {
		return stdrepo.findStudentDTOById(id).orElseThrow(()->new ResourceNotFound("Student is not found with id: "+id));
		
	}

	@Override
	public List<Student> getStudents() {
		return stdrepo.findAllStudent();
	}

	@Override
	public List<StudentDTO> getStudentDTOs() {
		List<Student> stdList= stdrepo.findAllStudent();
	
		return mapper.mapToStdDTO(stdList);
	}
		 
	
	@Override
	public List<StudentBookDTO> getStudentBookDTO() {
		 List<Student> stdDTOlist=stdrepo.findAll();
		return mapper.mapToStudentBookDTO(stdDTOlist);
	}

	@Override
	public Page<StudentBookDTO> getstdBookDTOPage(Pageable pageable) {
		Page<Student> studentPage=stdrepo.findAll(pageable);
		Page<StudentBookDTO> stdDTOPage=studentPage.map(new Function<Student, StudentBookDTO>(){
			@Override
			public StudentBookDTO apply(Student t) {
			return mapper.studentToStudentBookDTO(t);
			}});
		return stdDTOPage;
	}

	

}
