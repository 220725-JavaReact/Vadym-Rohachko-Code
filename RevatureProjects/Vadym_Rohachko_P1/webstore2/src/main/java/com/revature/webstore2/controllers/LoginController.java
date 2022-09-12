package com.revature.webstore2.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore2.dao.UserDao;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.models.User;
import com.revature.webstore2.services.UserService;
import com.revature.webstore2.utils.Logger;

public class LoginController extends HttpServlet {
	Logger logger = Logger.getInstance();

	String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");			
			
		UserService userService = new UserService(new UserDao());
		User currentUser = userService.prosessLogin(new User(email, pass));
		
		if (currentUser.getUserId() == 0) {
			// Failed to Login
			try (PrintWriter out = resp.getWriter()) {
				out.println(failedPage());
				logger.log(Logger.LogLevel.error, "Incorrect login/email = " + email);
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "LoginController error");
				logger.log(Logger.LogLevel.error, e.getMessage());
				e.printStackTrace();
			}
		} else {
			// Success
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "LoginController: successfull");
				// move to the page for registered customers
				currentUser.setCurrent(true);
				userService.changeUserData(currentUser);
				resp.sendRedirect("/webstore2/shopping");	
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "LoginController error");
				logger.log(Logger.LogLevel.error, e.getMessage());
				e.printStackTrace();
			}
		}		
	}

	
	private String failedPage() {
		return "<!DOCTYPE html>" 
	            + "<html>" 
				+ "<head><title>Register Page</title>" + cssTag + "</head>" 
	            + "<body>"
		// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>"
				+ "</div>"
				// END OF HEADER

				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore2/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore2/register\">Register</a>" + "</div>" + "</div>" + "</br>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<h2 style=\"text-align: center\">Failed to login...</h2>"
				+ "<h3 style=\"text-align: center\">Email and/or password is incorrect.</h3>"
				+ "<h3 style=\"text-align: center\"><a href=\"/webstore2/login\">Please, try again...</a></h3>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Inspired by w3school.</p>" + "</div>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
}


