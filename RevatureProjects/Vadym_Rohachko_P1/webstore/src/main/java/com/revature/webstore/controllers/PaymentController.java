package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore.models.User;
import com.revature.webstore.utils.Logger;

public class PaymentController 

extends HttpServlet {
	Logger logger = Logger.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {

			HttpSession session = req.getSession(false);
			User user = (User) session.getAttribute("webstore_current_user");

			if (user == null) {
				out.println(buildStorePage(new User()));
			} else {
				out.println(buildStorePage(user));
			}

			// LOGGER
			logger.log(Logger.LogLevel.info, "Cart loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private String buildStorePage(User user) {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		String part1 = "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Page</title>" + cssTag + "</head>"
				+ "<body>"
//      		// HEADER
				+ "<div class=\"header\">" + "<h1>Shopping Cart</h1>" + "<p6> (servlet)</p6>"
                + "<p>";
		
				String fname = "";
				String lname = "";
				 if(user.getfName() != null) {
					 fname = user.getfName();
					 lname = user.getlName();
				 } 				

				// <!-- Navigation Bar -->
				String part2 = fname + " " + lname +  "</p></div><div class=\"topnav\">" 
				+ "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "</div>" + "</div>" + "</br>"
				// END NAV

				// MAIN CONTENT
				+ "<div class=\"main-container\">" + "<div class=\"side\">" + "</div>" + "</div>"
				// END LEFT SIDE MENU
				+ "<h3 style=\"text-align:center;\">Payment successfull!</h3>"
				// MAIN CONTENT (right column)
				+ "<section class=\"content\">";

		// END OF MAIN SECTION
		String part3 = "</section>"
				// END OF MAIN CONTENT (right column)
				+ "</div>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<div class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";

		return part1 + part2 +  part3;
	}
}
