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

import com.next_u.notes.entities.Grade;
import com.next_u.notes.repository.CourseRepository;
import com.next_u.notes.repository.GradeRepository;
import com.next_u.notes.repository.StudentRepository;
import com.next_u.notes.services.GradeDTO;

@RestController
@RequestMapping("/grades")
public class GradeController {

	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("")
	public List<Grade> getgrades() {
		return gradeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Grade> getgradeById(@PathVariable Long id) {
		Grade grade = gradeRepository.findById(id).orElse(null);
		if (grade != null) {
			return ResponseEntity.ok(grade);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("")
	public ResponseEntity<Grade> postgrade(@RequestBody GradeDTO gradeDTO) {
		Grade grade = new Grade();
		grade.setGrade(gradeDTO.getGrade());
		grade.setStudent(studentRepository.findById(gradeDTO.getStudentId())
				.orElseThrow(() -> new RuntimeException("Student not found")));
		grade.setCourse(courseRepository.findById(gradeDTO.getCourseId())
				.orElseThrow(() -> new RuntimeException("Course not found")));
		gradeRepository.save(grade);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Grade> updategrade(@RequestBody GradeDTO gradeDTO, @PathVariable Long id) {
		Grade grade = gradeRepository.findById(id).orElse(null);
		if (grade != null) {
			grade.setGrade(gradeDTO.getGrade());
			grade.setStudent(studentRepository.findById(gradeDTO.getStudentId())
					.orElseThrow(() -> new RuntimeException("Student not found")));
			grade.setCourse(courseRepository.findById(gradeDTO.getCourseId())
					.orElseThrow(() -> new RuntimeException("Course not found")));
			return ResponseEntity.ok(gradeRepository.save(grade));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletegrade(@PathVariable Long id) {
		if (gradeRepository.findById(id) != null) {
			gradeRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
