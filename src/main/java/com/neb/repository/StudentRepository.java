package com.neb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neb.domain.Student;

@Repository   // optional if extending the JPA
public interface StudentRepository extends JpaRepository<Student, Long> {

	Boolean existsByEmail(String email);
	
	
}
