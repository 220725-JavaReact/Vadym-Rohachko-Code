package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore.dao.UserDao;
import com.revature.webstore.models.CustomerSession;
import com.revature.webstore.models.Inventory;
import com.revature.webstore.models.User;
import com.revature.webstore.services.UserService;
import com.revature.webstore.utils.Logger;

public class RegisterController extends HttpServlet {
	Logger logger = Logger.getInstance();

	String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
			+ "<link rel='stylesheet' type='text/css' href='css/register.css'>"
			+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		HttpSession session = req.getSession(); 

		resp.setContentType("text/html");

		User customer;
		UserService userService = new UserService(new UserDao());
		customer = userService.checkUserExists(email);

		if (customer.getUserId() > 0) {
			// Failed to Register
			try (PrintWriter out = resp.getWriter()) {
				out.println(buildFailedPage());
				// LOGGER
				logger.log(Logger.LogLevel.info, "Failed, customer already exists");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			userService.registerUser(new User(fName, lName, email, pass));
			customer = userService.checkUserExists(email);			
			if (customer.getUserId() != 0) {
				try (PrintWriter out = resp.getWriter()) {
					logger.log(Logger.LogLevel.info, "Registering customer = " + customer.getEmail());					
					
					List<Inventory> customerInventory = new ArrayList();
					CustomerSession customerSession = 
							new CustomerSession(customer.getEmail(),
									customer.getfName(), customer.getlName(), 
									customerInventory, false);
					
					// set the separate record in the common session for each customer
					String currCust = customer.getEmail();
					session.setAttribute(currCust, customerSession);
					
					// display page   
					out.println(buildSuccessPage());					
					logger.log(Logger.LogLevel.info, "Registered successfully"  + currCust);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// Failed to register
				try (PrintWriter out = resp.getWriter()) {
					out.println(buildFailedPage());					
					logger.log(Logger.LogLevel.info, "Failed to register");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String buildSuccessPage() {
		return "<!DOCTYPE html>" + "<html>" + "<head><title>Register Page</title>" + cssTag + "</head>"
				+ "<body>"
				// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>"
				+ "</div>"
				// END OF HEADER

				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore/log\">Login</a>" + "</div>" + "</div>"
				+ "</br>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<h2 style=\"text-align: center\">Registration successul!</h2>"
				+ "<h3 style=\"text-align: center\"><a href=\"/webstore/log\">Login, please...</a></h3>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}

	private String buildFailedPage() {
		return "<!DOCTYPE html>" + "<html>" + "<head><title>Register Page</title>" + cssTag + "</head>" + "<body>"
		// HEADER
				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" + "<p>With a <b>flexible</b> layout.</p>"
				+ "</div>"
				// END OF HEADER

				// NAVBAR
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore/log\">Login</a>" + "</div>" + "</div>" + "</br>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<h2 style=\"text-align: center\">Failed to register...</h2>"
				+ "<h3 style=\"text-align: center\">User with the same email already exists.</h3>"
				+ "<h3 style=\"text-align: center\"><a href=\"/webstore/reg\">Please, try again...</a></h3>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}

}
