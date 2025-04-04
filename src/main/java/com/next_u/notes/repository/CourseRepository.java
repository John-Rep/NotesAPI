package com.next_u.notes.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.next_u.notes.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	Set<Course> findByIdIn(Long[] categories);
}
