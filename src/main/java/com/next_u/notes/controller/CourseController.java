package com.next_u.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next_u.notes.entities.Course;
import com.next_u.notes.repository.CourseRepository;
import com.next_u.notes.services.CourseDTO;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/")
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@PostMapping("/")
	public Course postCourse(@RequestBody CourseDTO courseDTO) {
		Course course = new Course();
		course.setTitle(courseDTO.getTitle());
		return courseRepository.save(course);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		if (course != null) {
			course.setTitle(courseDTO.getTitle());
			return ResponseEntity.ok(courseRepository.save(course));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
		if (courseRepository.findById(id) != null) {
			courseRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
