package com.revature.qc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.qc.models.Subject;

public interface SubjectDao extends JpaRepository<Subject, Long>  {

}
