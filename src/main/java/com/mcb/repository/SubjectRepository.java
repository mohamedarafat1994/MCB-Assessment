package com.mcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcb.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
