package com.revature.webstore2.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore2.dao.*;
import com.revature.webstore2.models.*;
import com.revature.webstore2.services.*;
import com.revature.webstore2.utils.Logger;

public class StoreController extends HttpServlet {
	Logger logger = Logger.getInstance();	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/webstore2/", "");
		//resp.setContentType("application/json");

		switch (URI) {
		case "login":
			PrintWriter out_ = resp.getWriter();
			displayLoginPage(resp);
			break;
		case "register":
			displayRegisterPage(resp);
			break;
		default:
			// In the event that someone tried to access get request that isn't allowed
			// I've used the super method of doGet because that already returns a 405 method
			// not allowed in a proper way
			super.doGet(req, resp);
			break;
		}
	}

	private void displayLoginPage(HttpServletResponse resp) {
		resp.setStatus(200);
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println(loginPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayRegisterPage(HttpServletResponse resp) {
		resp.setStatus(200);
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println(registerPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void displayFailedPage(HttpServletResponse resp) {
		resp.setStatus(400);
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println(failedPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String loginPage() {
		return "<!DOCTYPE html>" 
		        + "<html>" 
				+ "<head>"
				+ "<title>Login</title>"
				+ " <link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "</head>" 
				+ "<body>"
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" 
				+ "</div>"
				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore2/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore2/register\">Register</a>" + "</div>" + "</div>" + "</br>"
				// LOGIN FORM
				+ "<form class=\"login-form\" action=\"/webstore2/log\" method=\"post\">" + "<p>LOGIN</p>"
				+ "<label for=\"name\"><b>Username</b></label>"
				+ "<input class=\"login-form__email\" type=\"text\" placeholder=\"Enter Username\" name=\"email\" required>"
				+ "<label for=\"pass\"><b>Password</b></label>"
				+ "<input class=\"login-form__pass\" type=\"password\" placeholder=\"Enter Password\" name=\"pass\" required>"
				+ "<button class=\"login-form__button\" type=\"submit\">Login</button>"
				+ "<div class=\"login-form__cancel-container\" style=\"background-color:#f1f1f1\"> "
				+ "<a href=\"/webstore2/\">"
				+ "<button type=\"button\" class=\"login-form__button login-form__button_type_cancel\">Cancel</button>"
				+ "</a>"
				+ "<span class=\"login-form__register\">Register <a href=\"/webstore2/register\">new account</a></span>"
				+ "</form> "
				// FOOTER
				+ "<footer class=\"footer\">"
				+ "<p>Based on w3school.</p>" + "</footer>"
				// END OF HTML
				+ "<script src=\"js/main.js\"></script>" 
				+ "</body>" 
				+ "</html>";
	}

	private String registerPage() {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Register Page</title>" + cssTag + "</head>" + "<body>"
		// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" 
				+ "</div>"
				// END OF HEADER

//NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore2/\">Home</a>"
//+ "<a class=\"topnav__link\" href=\"#news\">News</a>"
//+ "<a class=\"topnav__link\" href=\"#contact\">Contact</a>"
//+ "<div class=\"dropdown\">"
//+ "<button class=\"dropdown__dropbtn\">Categories"
//+ "<i class=\"fa fa-caret-down\"></i>"
//+ "</button>"
//+ "<div class=\"dropdown__content\">"
//+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Music</a>"
//+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Soft</a>"
//+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Games</a>"
//+ "</div>"
//+ "</div>"
				+ "<div class=\"topnav topnav_position_right\">"
//+ "<a class=\"topnav__link \" href=\"#search\">Cart</a>"
				+ "<a class=\"topnav__link \" href=\"/webstore2/login\">Login</a>" + "</div>" + "</div>" + "</br>"
////END NAVBAR

//REGISTER FORM
				+ "<form class=\"register-form\" method = \"post\" action=\"/webstore2/reg\">"
				+ " <div class=\"register-form__inner-container\">" + "<p>REGISTER</p>"
				+ "<hr class=\"register-form__hr\">"

				+ "<label for=\"fName\"><b>First Name</b></label>"
				+ "<input class=\"register-form__input-text\" type=\"text\" placeholder=\"Enter first name\" name=\"fName\" id=\"fName\" required>"

				+ "<label for=\"lName\"><b>Last Name</b></label>"
				+ "<input class=\"register-form__input-text\" type=\"text\" placeholder=\"Enter last name\" name=\"lName\" id=\"lName\" required>"

				+ "<label for=\"email\"><b>Email</b></label>"
				+ "<input class=\"register-form__input-text\" type=\"text\" placeholder=\"Enter Email\" name=\"email\" id=\"email\" required>"

				+ "<label for=\"psw\"><b>Password</b></label>"
				+ "<input class=\"register-form__input-pass\" type=\"password\" placeholder=\"Enter Password\" name=\"pass\" id=\"pass\" required>"

				+ "<hr class=\"register-form__hr\">"
				+ "<button type=\"submit\" class=\"register-form__btn\">Register</button>" + "</div>"

				+ "<div class=\"register-form__inner-container register-form__signin\">"
				+ "<p>Already have an account? <a class=\"register-form__link\" href=\"/webstore2/login\">Sign in</a>.</p>"
				+ "</div>" + "</form>"
				// END REGISTER FORM

				// FOOTER
				+  "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER
				// END OF HTML
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}	
	
	private String failedPage() {
		return "<!DOCTYPE html>" 
	            + "<html>" 
				+ "<head><title>Register Page</title>" 
				+ "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
	            + "</head>" 
	            + "<body>"
		        // HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" + "<p>With a <b>flexible</b> layout.</p>"
				+ "</div>"
				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore2/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore2/register\">Register</a>" + "</div>" + "</div>" + "</br>"
				// MAIN CONTENT
				+ "<h2 style=\"text-align: center\">Failed to login...</h2>"
				+ "<h3 style=\"text-align: center\">Email and/or password is incorrect.</h3>"
				+ "<h3 style=\"text-align: center\"><a href=\"/webstore2/login\">Please, try again...</a></h3>"
				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Inspired by w3school.</p>" + "</div>"
				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
	
	
}

