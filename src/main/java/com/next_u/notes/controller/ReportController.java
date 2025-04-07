package com.next_u.notes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next_u.notes.entities.Grade;
import com.next_u.notes.repository.GradeRepository;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private GradeRepository gradeRepository;

	@GetMapping("student/{id}")
	public ResponseEntity<Map<String, Object>> GetReportByStudentId(@PathVariable Long id) {
		List<Grade> grades = gradeRepository.findByStudentId(id);
		if (grades.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Map<String, Double> averagesByCourse = grades.stream().collect(Collectors
				.groupingBy(grade -> grade.getCourse().getTitle(), Collectors.averagingDouble(Grade::getGrade)));
		double overallAverage = grades.stream().mapToDouble(Grade::getGrade).average().orElse(0.0);
		Map<String, Object> report = new HashMap<>();
		report.put("Student Name", grades.get(0).getStudent().getName());
		report.put("Average Grades", averagesByCourse);
		report.put("Overall Average Grade", overallAverage);
		return ResponseEntity.ok(report);
	}

	@GetMapping("course/{id}")
	public ResponseEntity<Map<String, Object>> GetReportByCourseId(@PathVariable Long id) {
		List<Grade> grades = gradeRepository.findByCourseId(id);
		if (grades.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Map<String, Double> averagesByStudent = grades.stream().collect(Collectors
				.groupingBy(grade -> grade.getStudent().getName(), Collectors.averagingDouble(Grade::getGrade)));
		double overallAverage = grades.stream().mapToDouble(Grade::getGrade).average().orElse(0.0);
		Map<String, Object> report = new HashMap<>();
		report.put("Course Title", grades.get(0).getStudent().getName());
		report.put("Average Grades", averagesByStudent);
		report.put("Overall Average Grade", overallAverage);
		return ResponseEntity.ok(report);
	}

}
