package com.mcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcb.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
