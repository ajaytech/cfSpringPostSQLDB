package com.abcComp.hr.lms.code.controller;


import com.abcComp.hr.lms.code.entities.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface StudentRepo extends JpaRepository<Student, Long> {

	
	@Query(value = "SELECT * FROM public.student where Lower(location_new) like %?1%",nativeQuery = true)
	List<Student> fuzzyFindByLocation(String location);
	

}