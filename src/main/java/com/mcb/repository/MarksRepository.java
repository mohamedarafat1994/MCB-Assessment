package com.mcb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcb.models.Marks;
import com.mcb.models.Student;
import com.mcb.payload.response.StudentSubjects;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long>{

	//@Query(value = "SELECT * FROM MARKS WHERE STUDENT_ID = ?1", nativeQuery = true)
	List<Marks> findByStudent(Student student);

	@Query(value = "SELECT M.* FROM MARKS M INNER JOIN SUBJECTS S ON M.SUBJECT_ID = S.ID WHERE M.STUDENT_ID = :studentId", nativeQuery = true)
	List<Marks> getAllMarksByStudentId(Long studentId);

}
