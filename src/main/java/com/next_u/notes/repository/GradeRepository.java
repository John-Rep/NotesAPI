package com.next_u.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.next_u.notes.entities.Grade;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
	List<Grade> findByStudentId(Long id);

	List<Grade> findByCourseId(Long id);
}
