package com.revature.qc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.qc.dao.SubjectDao;
import com.revature.qc.models.Subject;
import com.revature.qc.models.Subject;
import com.revature.qc.utils.Logger;

@RestController
@RequestMapping("/qc/subjects")
public class SubjectController {
	Logger logger = Logger.getInstance();

	@Autowired
	private SubjectDao subjectDao;

	@GetMapping
	public List<Subject> getAllSubject() {
		logger.log(Logger.LogLevel.info, "Get all subjects");
		return this.subjectDao.findAll();
	}

	@GetMapping("/{subject_id}")
	public Subject getUserById(@PathVariable(value = "subject_id") long subjectId) {
		Subject existingSubject = this.subjectDao.findById(subjectId)
				.orElseThrow(() -> new NullPointerException("Subject not found with id : " + subjectId));
		logger.log(Logger.LogLevel.info, "Get subject = " + existingSubject.toString());
		return existingSubject;
	}

	@PostMapping
	public Subject createUser(@RequestBody Subject subject) {
		Subject newSubject = this.subjectDao.save(subject);
		logger.log(Logger.LogLevel.info, "Added subject = " + newSubject.toString());
		return newSubject;
	}

	@PutMapping("/{subject_id}")
	public Subject updateSubject(@RequestBody Subject subject, @PathVariable("subject_id") long subjectId) {
		Subject existingSubject = this.subjectDao.findById(subjectId)
				.orElseThrow(() -> new NullPointerException("Subject not found with id : " + subjectId));
		existingSubject.setSubject(subject.getSubject());
		logger.log(Logger.LogLevel.info, "Updated subject = " + existingSubject.toString());
		return this.subjectDao.save(existingSubject);
	}

	@DeleteMapping("/{subject_id}")
	public ResponseEntity<Subject> deleteSubject(@PathVariable("subject_id") long subjectId) {
		Subject existingSubject = this.subjectDao.findById(subjectId)
				.orElseThrow(() -> new NullPointerException("Subject not found with id : " + subjectId));
		this.subjectDao.delete(existingSubject);
		logger.log(Logger.LogLevel.info, "Deleted subject = " + existingSubject.toString());
		return ResponseEntity.ok().build();
	}

}
