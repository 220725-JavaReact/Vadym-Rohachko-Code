package com.revature.webapp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.webapp4.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	
}
