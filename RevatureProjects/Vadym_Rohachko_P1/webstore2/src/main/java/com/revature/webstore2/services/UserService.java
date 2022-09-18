package com.revature.webstore2.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.User;
import com.revature.webstore2.utils.Logger;

public class UserService {
	Logger logger = Logger.getInstance();
	private Dao<User> userDao;

	public UserService(Dao<User> userDao) {
		this.userDao = userDao;
	}

	public void registerUser(User user) {
		userDao.addInstance(user);
	}

	private List<User> getAllUsers() {
		List<User> users = userDao.getAllInstance();
		return users;
	}
	
	public List<User> getUsers() {
		return getAllUsers();
	}

	public User getUser(String email) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getEmail().equals(email)).findFirst();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			return new User(0, "No user found", "", "", "", timestamp);
		}
		return userOptional.get();
	}

	public User getUser(int id) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getUserId() == id).findAny();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			return new User(0, "No user found", "", "", "", timestamp);
		}
		return userOptional.get();
	}
	
	public User getCurrentUser() {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = Optional.ofNullable(users.stream()
		// .peek(u -> System.out.println(u.getEmail()))
		.filter(u -> u.getCurrent() == true).findAny().orElse(null));
		//.filter(u -> u.getCurrent() == true).findFirst();
		
//		User userTemp = new User();
//		for(User user : users) {
//			if(user.getCurrent() == true) {
//				userTemp = user;
//			}
//		}
		
		if (userOptional.isEmpty()) {
//		if (!userTemp.getCurrent()) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		return new User(0, "No user found", "", "", "", timestamp);
	}	
	//return userTemp;
		return userOptional.get();
}
	
	public boolean isLoggedin() {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getCurrent() == true).findAny();

		if (userOptional.isEmpty()) {
			return false;
		}
		return userOptional.get().getCurrent();
	}

	public User prosessLogin(User user) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getEmail().equals(user.getEmail()) && u.getPass().equals(user.getPass())).findFirst();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			return new User(0, "Incorrect email/password", "", "", "", timestamp);
		}
		return userOptional.get();
	}
	
	public void changeUserData(User user) {
		this.userDao.updateInstance(user);
	}
	
	public User getUserBySessionId(String sessionId) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getSessionId().equals(sessionId)).findAny();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			return new User(0, "No user found", "", "", "", timestamp);
		}		
		return userOptional.get();
	}
}


