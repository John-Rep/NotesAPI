package com.next_u.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next_u.notes.entities.Student;
import com.next_u.notes.repository.StudentRepository;
import com.next_u.notes.services.StudentDTO;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("")
	public List<Student> getstudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getstudentById(@PathVariable Long id) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			return ResponseEntity.ok(student);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	public ResponseEntity<Student> poststudent(@RequestBody StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.getName());
		student.setAge(studentDTO.getAge());
		studentRepository.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updatestudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student != null) {
			student.setName(studentDTO.getName());
			student.setAge(studentDTO.getAge());
			return ResponseEntity.ok(studentRepository.save(student));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletestudent(@PathVariable Long id) {
		if (studentRepository.findById(id) != null) {
			studentRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
