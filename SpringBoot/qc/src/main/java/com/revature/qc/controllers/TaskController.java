package com.revature.qc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.qc.utils.Logger;
import com.revature.qc.dao.TaskDao;
import com.revature.qc.models.Task;

@CrossOrigin("*")
@RestController
@RequestMapping("/qc/tasks")
public class TaskController {
	Logger logger = Logger.getInstance();

	@Autowired
	private TaskDao taskDao;

	
	@GetMapping
	public List<Task> getAllTasks() {
		logger.log(Logger.LogLevel.info, "Get all tasks");
		return this.taskDao.findAll();
	}
	
	
	@GetMapping("/{task_id}")
	public Task getUserById(@PathVariable (value = "task_id") long taskId) {
		Task existingTask = this.taskDao
				.findById(taskId)
				.orElseThrow(() -> new NullPointerException("User not found with id : " + taskId));
		logger.log(Logger.LogLevel.info, "Get user = " + existingTask.toString());
		return existingTask;
	}
	
	
	@PostMapping
	public Task createUser(@RequestBody Task task) {	
		Task newTask = this.taskDao.save(task);
		logger.log(Logger.LogLevel.info, "Added task = " + newTask.toString());
		return newTask;
	}
	
	
	@PutMapping("/{task_id}")
	public Task updateTask(@RequestBody Task task, @PathVariable ("task_id") long taskId) {
		Task existingTask = this.taskDao.findById( taskId)
		.orElseThrow(() -> new NullPointerException("Task not found with id : " + taskId));
		existingTask.setQuestion(task.getQuestion());
		existingTask.setAnswer(task.getAnswer());
		logger.log(Logger.LogLevel.info, "Updated task = " + existingTask.toString());
		return this.taskDao.save(existingTask);
	}
	
	@CrossOrigin
	@DeleteMapping("/{task_id}")
	public ResponseEntity<Task> deleteUser(@PathVariable ("task_id") long taskId){
	    Task existingTask = this.taskDao.findById(taskId)
				.orElseThrow(() -> new NullPointerException("Task not found with id : " + taskId));
		this.taskDao.delete(existingTask);
		logger.log(Logger.LogLevel.info, "Deleted user = " + existingTask.toString());
		return ResponseEntity.ok().build();
	}

}
