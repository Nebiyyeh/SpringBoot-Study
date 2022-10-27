package com.neb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neb.domain.Student;
import com.neb.dto.StudentDTO;

@Repository   // optional if extending the JPA
public interface StudentRepository extends JpaRepository<Student, Long> {

	//custom query
	// JPQuery (HQL)
	@Query("SELECT s FROM Student s WHERE s.grade=:pGrade")
	List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
	
	//SQL
//	@Query( value="SELECT * FROM Student s WHERE s.grade=:pGrade", nativeQuery=true)
//	List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
//	
	
	@Query("SELECT new com.neb.dto.StudentDTO(s) FROM Student s WHERE id=:id")
	Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
	
	// derived query
	Boolean existsByEmail(String email);
	
	@Query("FROM Student")
	List<Student> findAllStudent();
	
	@EntityGraph(attributePaths = {"books"})  //books will be get eagerly
	List<Student> findAll();
	
	
}