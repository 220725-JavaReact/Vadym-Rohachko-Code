package com.revature.webstore2.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore2.dao.UserDao;
import com.revature.webstore2.models.User;
import com.revature.webstore2.services.UserService;
import com.revature.webstore2.utils.Logger;

@WebServlet(name = "/logout")
public class LogoutController extends HttpServlet {
	Logger logger = Logger.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = new UserService(new UserDao());
		User currentUser = userService.getCurrentUser();
		logger.log(Logger.LogLevel.info, "LogoutController" + currentUser.getlName() + "  " +currentUser.getEmail());
		currentUser.setCurrent(false);
		userService.changeUserData(currentUser);		
		resp.sendRedirect("/webstore2/");
	}
}


