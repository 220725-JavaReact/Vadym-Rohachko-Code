package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.webstore.dao.InventoryDao;
import com.revature.webstore.models.Inventory;
import com.revature.webstore.services.InventoryService;
import com.revature.webstore.servlets.Login;
import com.revature.webstore.utils.Logger;

public class StoreController extends HttpServlet {
	// LOGER
	Logger logger = Logger.getInstance();	
	
//	 String jsonString;
//		private static InventoryService inventoryService = new InventoryService(new InventoryDao());
//		List<Inventory> inventories = inventoryService.getAllInventories();
//		private static ObjectMapper objmap = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// webstore is the project name (case-sensitive)
		final String URI = req.getRequestURI().replace("/webstore/", "");
		
//		resp.getWriter().println(inventories.toArray().length + "</br>");
//		for(Inventory inv : inventories) {
//			resp.getWriter().println(inv.getProduct() + "</br>");
//		}
		  
//			jsonString = objmap.writeValueAsString(inventories);
//		    resp.getWriter().println(jsonString);

		// We need to specify that what we are about to give is a JSON information
		resp.setContentType("application/json");

		switch (URI) {
		case "log":
			logger.log(Logger.LogLevel.info, "Login page called");
			displayLoginPage(resp);
			break;
		case "reg":
			logger.log(Logger.LogLevel.info, "Register page called");
			displayRegisterPage(resp);
			break;
//		case "cart":
//			RequestDispatcher redis = req.getRequestDispatcher("/cart");
//			redis.forward(req, resp);
//			resp.getWriter().println("I am in store");
//			break;
		default:
			// In the event that someone tried to access get request that isn't allowed
			// I've used the super method of doGet because that already returns a 405 method
			// not allowed in a proper way
			super.doGet(req, resp);
			break;
		}
	}

	private void displayLoginPage(HttpServletResponse resp) {
		logger.log(Logger.LogLevel.info, "Login page loaded");
		resp.setStatus(200);
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println(showAnonLoginPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayRegisterPage(HttpServletResponse resp) {
		logger.log(Logger.LogLevel.info, "Register page loaded");
		resp.setStatus(200);
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html");
			out.println(showAnonRegisterPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String showAnonLoginPage() {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel='stylesheet' type='text/css' href='css/login.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Login</title>" + cssTag + "</head>" + "<body>"
		// header
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" 
				+ "</div>"

				// <!-- Navigation Bar -->
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				// + "<a class=\"topnav__link\" href=\"#news\">News</a>"
				// + "<a class=\"topnav__link\" href=\"#contact\">Contact</a>"
				// + "<div class=\"dropdown\">"
				// + "<button class=\"dropdown__dropbtn\">Categories"
				// + "<i class=\"fa fa-caret-down\"></i>"
				// + "</button>"
				// + "<div class=\"dropdown__content\">"
				// + "<a class=\"topnav__link dropdown__link\" href=\"#\">Music</a>"
				// + "<a class=\"topnav__link dropdown__link\" href=\"#\">Soft</a>"
				// + "<a class=\"topnav__link dropdown__link\" href=\"#\">Games</a>"
				// + "</div>"
				// + "</div>"
				+ "<div class=\"topnav topnav_position_right\">"
				// + "<a class=\"topnav__link \" href=\"#search\">Cart</a>"
				+ "<a class=\"topnav__link \" href=\"/webstore/reg\">Register</a>" + "</div>" + "</div>" + "</br>"
//						// END NAV

				// LOGIN FORM
				+ "<form class=\"login-form\" action=\"/webstore/login\" method=\"post\">" + "<p>LOGIN</p>"
				+ "<label for=\"name\"><b>Username</b></label>"
				+ "<input class=\"login-form__email\" type=\"text\" placeholder=\"Enter Username\" name=\"email\" required>"
				+ "<label for=\"pass\"><b>Password</b></label>"
				+ "<input class=\"login-form__pass\" type=\"password\" placeholder=\"Enter Password\" name=\"pass\" required>"
				+ "<button class=\"login-form__button\" type=\"submit\">Login</button>"
				//+ " <label>"
			   // + " <input type=\"checkbox\" checked=\"checked\" name=\"remember\"> Remember me" + " </label>"
				+ "<div class=\"login-form__cancel-container\" style=\"background-color:#f1f1f1\"> "
				+ "<a href=\"/webstore/\">"
				+ "<button type=\"button\" class=\"login-form__button login-form__button_type_cancel\">Cancel</button>"
				+ "</a>"
				+ "<span class=\"login-form__register\">Register <a href=\"/webstore/reg\">new account</a></span>"
				+ "</form> "
				// END LOGIN FORM

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>!Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER
				// END OF HTML
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}

	private String showAnonRegisterPage() {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel='stylesheet' type='text/css' href='css/register.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Register Page</title>" + cssTag + "</head>" + "<body>"
		// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" 
				+ "</div>"
				// END OF HEADER

//NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
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
				+ "<a class=\"topnav__link \" href=\"/webstore/log\">Login</a>" + "</div>" + "</div>" + "</br>"
////END NAVBAR

//REGISTER FORM
				+ "<form class=\"register-form\" method = \"post\" action=\"/webstore/register\">"
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

				// + "<label for=\"psw-repeat\"><b>Repeat Password</b></label>"
				// + "<input type=\"password\" placeholder=\"Repeat Password\"
				// name=\"psw-repeat\" id=\"psw-repeat\" required>"
				+ "<hr class=\"register-form__hr\">"

				// + "<p>By creating an account you agree to our <a href=\"#\">Terms &
				// Privacy</a>.</p>"
				+ "<button type=\"submit\" class=\"register-form__btn\">Register</button>" + "</div>"

				+ "<div class=\"register-form__inner-container register-form__signin\">"
				+ "<p>Already have an account? <a class=\"register-form__link\" href=\"/webstore/log\">Sign in</a>.</p>"
				+ "</div>" + "</form>"
				// END REGISTER FORM

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER
				// END OF HTML
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
}
