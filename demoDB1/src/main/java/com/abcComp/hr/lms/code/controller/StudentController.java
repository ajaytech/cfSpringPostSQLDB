package com.abcComp.hr.lms.code.controller;

import com.abcComp.hr.lms.code.entities.Student;

import java.io.*; 
import java.util.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController{
	
	@Autowired
	StudentRepo repoObj;
	
	
	
	@RequestMapping("/countRecords")
	public long get() {
		
		return repoObj.count();

	}
	
	@RequestMapping("/students")
	public List<Student> getAll() {
		
		
		return repoObj.findAll();
		

	}
	
	@RequestMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		Optional<Student> student = repoObj.findById(id);

		if (!student.isPresent())
			return new Student();

		return student.get();
	}
	
	
	@RequestMapping("/studentsbylocation/{location}")
	public List<Student> fuzzyGetStudent(@PathVariable String location) {
		List<Student> student = repoObj.fuzzyFindByLocation(location);

		//System.out.println(student.get());
		
		return student;
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE,value="/students/{id}")
	public Student deleteStudent(@PathVariable long id) {
		Optional<Student> student = repoObj.findById(id);

		if (!student.isPresent())
			return new Student();	
		
		repoObj.deleteById(id);
			
		return student.get();
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/students")
	public Student addStudent(@RequestBody Student student) {
		
		Student studentObj = repoObj.save(student);
		
		
		return studentObj;
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/students/{id}")
	public Student UpdateStudent(@RequestBody Student student,@PathVariable long id) {
		
		Optional<Student> studentObj = repoObj.findById(id);
		
		if (!studentObj.isPresent())
			return new Student();
		
		repoObj.save(student);
		
		
		return student;
	}
	
}
