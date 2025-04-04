package com.next_u.notes.services;

public class CourseDTO {

	public CourseDTO() {
	}

	public CourseDTO(String title) {
		this.setTitle(title);
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
