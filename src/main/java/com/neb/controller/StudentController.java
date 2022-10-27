package com.neb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neb.domain.Student;
import com.neb.dto.StudentBookDTO;
import com.neb.dto.StudentDTO;
import com.neb.exception.ConflictException;
import com.neb.service.StudentService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/student")
@AllArgsConstructor
@NoArgsConstructor
public class StudentController {
	
	@Autowired
	private StudentService stdService;
	
	@GetMapping("/welcome")
	public String welcome(HttpServletRequest servletRequest) {
		
		return "Welcome to the Student Controller via " + servletRequest.getServletPath();
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody StudentDTO studentDTO){
		stdService.saveStudent(studentDTO);
		Map<String, String> message=new HashMap<>();
		message.put("message", "Student is created successfully");
		message.put("succes", "true");
		
		return new ResponseEntity<>(message,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> allStudents=stdService.getAllStudents();
		return ResponseEntity.ok(allStudents);
	}
	
	@GetMapping("/query")
	public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
		Student student= stdService.getStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentByPath(@PathVariable("id") Long id){
		Student student= stdService.getStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO){
		
		stdService.updateStudent(id, studentDTO);
		
		Map<String, String> map=new HashMap<>();
		map.put("message", "Student updated successfully");
		map.put("success", "true");
		
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id){
		stdService.deleteStudent(id);
		Map<String, String> map=new HashMap<>();
		map.put("message", "Student deleted successfully");
		map.put("success", "true");
		
		return ResponseEntity.ok(map);
	}
	
	
	@GetMapping("/email")
	public ResponseEntity<Map<String, String>> checkMail(@RequestParam String email){
		
		Boolean exists=stdService.existsByEmail(email);
		
		Map<String, String> map=new HashMap<>();
		map.put("doesEmailExist", exists.toString());
	
		return ResponseEntity.ok(map);
	}
//	
	@GetMapping("/pages")
	public ResponseEntity<Page<Student>> getStudentPage(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sort") String prop, @RequestParam(value="direction", required=false, defaultValue="DESC" ) Direction direction ){
		 
		Pageable pageable= PageRequest.of(page, size, Sort.by(direction, prop));
		
		Page<Student> studentPage= stdService.getStudentPage(pageable);
		
		return ResponseEntity.ok(studentPage);
	}
	
	//http://localhost:8080/student/grade/11
	@GetMapping("grade/{grade}")
	public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade ){
		List<Student> students=stdService.findAllEqualsGrade(grade);
		return ResponseEntity.ok(students);
	}
	
	
	//http://localhost:8080/student/grade?grade=12
	@GetMapping("grade")
	public ResponseEntity<List<Student>> getStudentsEqualsGradeWithQuery(@RequestParam("grade") Integer grade ){
		List<Student> students=stdService.findAllEqualsGrade(grade);
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/dto")
	public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){
		StudentDTO stdDTO= stdService.findStudentDTOById(id);
		return ResponseEntity.ok(stdDTO);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> allStd=stdService.getStudents();
		return ResponseEntity.ok(allStd);
	}
	
	@GetMapping("/list/dto")
	public ResponseEntity<List<StudentDTO>> getStudentDTOList(){
		List<StudentDTO> stdDTOList= stdService.getStudentDTOs();
		return ResponseEntity.ok(stdDTOList);
		}
	
	
	@GetMapping("/studentbook")
	public ResponseEntity<List<StudentBookDTO>> getStudentBookDTOs(){
		List<StudentBookDTO> stdBookDTOList= stdService.getStudentBookDTO();
		return ResponseEntity.ok(stdBookDTOList);
		}
	
	@GetMapping("/studentbook/pages")
	public ResponseEntity<Page<StudentBookDTO>> getStudentBookDTOPage(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam(value="sort") String prop, @RequestParam(value="direction", required=false, defaultValue="DESC" ) Direction direction ){
		 
		Pageable pageable= PageRequest.of(page, size, Sort.by(direction, prop));
		
		Page<StudentBookDTO> studentBookDTOPage= stdService.getstdBookDTOPage(pageable);
		
		return ResponseEntity.ok(studentBookDTOPage);
		
	}
}
	
	
