package com.next_u.notes.services;

public class GradeDTO {

	public GradeDTO() {
	}

	public GradeDTO(double grade, Long student_id, Long course_id) {
		this.grade = grade;
		this.student_id = student_id;
		this.course_id = course_id;
	}

	private double grade;
	private Long student_id;
	private Long course_id;

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Long getStudentId() {
		return student_id;
	}

	public void setStudentId(Long student_id) {
		this.student_id = student_id;
	}

	public Long getCourseId() {
		return course_id;
	}

	public void setCourseId(Long course_id) {
		this.course_id = course_id;
	}

}
