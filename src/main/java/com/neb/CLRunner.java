//package com.neb;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import com.neb.controller.StudentController;
//import com.neb.domain.Student;
//import com.neb.service.StudentService;
//
//@Component
//// ApplicationRunner can also be used
//public class CLRunner implements CommandLineRunner  {
//
//	@Autowired
//	StudentService stdService;
//	
//	@Autowired 
//	StudentController stdController;
//	
//	//Once application run completely, this method will be called
//	@Override
//	public void run(String... args) throws Exception {
////		List<Student> stdList=stdService.getAllStudents();
////		
////		stdList.forEach(s->System.out.println(s.getFirstName()+" "+s.getLastName()));
////
////		ResponseEntity<Student> response= stdController.getStudent(3L);
////		
////		System.out.println(response.getBody().getFirstName());
//		
//	}
//	
//	//netstat -ano | findstr :8080
//
//}
