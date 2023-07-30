package com.mcb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcb.models.Group;
import com.mcb.models.Marks;
import com.mcb.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	//@Query(value = "SELECT S.* FROM STUDENTS S INNER JOIN SUBJECTTEACHER T ON S.GROUP_ID = T.GROUP_ID WHERE T.ID = :teacherId", nativeQuery = true)
	List<Student> findByGroup(Group group);
	
}
