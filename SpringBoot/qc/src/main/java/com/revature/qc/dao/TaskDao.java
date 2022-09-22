package com.revature.qc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.qc.models.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

}
