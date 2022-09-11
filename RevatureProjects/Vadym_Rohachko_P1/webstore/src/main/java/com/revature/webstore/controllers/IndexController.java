package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			out.println(buildFailedPage());
			}
	}
	
	
	
	
	private String buildFailedPage() {
		return "<!DOCTYPE html>" 
	            + "<html>" 
				+ "<head><title>Register Page</title></head>" 
	            + "<body>"
		// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" + "<p>With a <b>flexible</b> layout.</p>"
				+ "</div>"
				// END OF HEADER

				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore/lreg\">Register</a>" + "</div>" + "</div>" + "</br>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<h2 style=\"text-align: center\">Failed to login...</h2>"
				+ "<h3 style=\"text-align: center\">Email and/or password is incorrect.</h3>"
				+ "<h3 style=\"text-align: center\"><a href=\"/webstore/log\">Please, try again...</a></h3>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}

}
