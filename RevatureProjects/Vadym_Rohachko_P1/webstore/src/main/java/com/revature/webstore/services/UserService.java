package com.revature.webstore.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.revature.webstore.interfaces.IDao;
import com.revature.webstore.models.User;
import com.revature.webstore.utils.Logger;

public class UserService {
	private IDao<User> userdao;
	Logger logger = Logger.getInstance();

	public UserService(IDao<User> userdao) {
		this.userdao = userdao;
	}

	public void registerUser(User user) {
		userdao.addInstance(user);
	}

	private List<User> getAllUsers() {
		List<User> users = userdao.getAllInstance();
		return users;
	}

	public User checkUserExists(String email) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				// .peek(u -> System.out.println(u.getEmail()))
				.filter(u -> u.getEmail().equals(email)).findFirst();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			logger.log(Logger.LogLevel.info, "UserService User = null");
			return new User(0, "No user found", "", "", "", timestamp);
		}
		logger.log(Logger.LogLevel.info, "lName = " + userOptional.get().getlName());
		return userOptional.get();
	}

	public User prosessLogin(User user) {
		List<User> users = this.getAllUsers();
		Optional<User> userOptional = users.stream()
				.filter(u -> u.getEmail().equals(user.getEmail()) && u.getPass().equals(user.getPass()))
				.findFirst();

		if (userOptional.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			return new User(0, "Incorrect email/password", "", "", "", timestamp);
		}
		return userOptional.get();
	}
}
