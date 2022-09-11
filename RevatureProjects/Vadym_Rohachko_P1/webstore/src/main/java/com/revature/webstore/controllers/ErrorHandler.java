package com.revature.webstore.controllers;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class ErrorHandler extends HttpServlet {

	// Method to handle GET method request.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

		if (requestUri == null) {
			requestUri = "Unknown";
		}

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Error/Exception Information";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n");

		if (throwable == null && statusCode == null) {
			out.println("<h2>Error information is missing</h2>");
			out.println("Please return to the <a href=\"" + response.encodeURL("http://localhost:8080/")
					+ "\">Home Page</a>.");
		} else if (statusCode != null) {
			out.println("The status code : " + statusCode);
		} else {
			out.println("<h2>Error information</h2>");
			out.println("Servlet Name : " + servletName + "</br></br>");
			out.println("Exception Type : " + throwable.getClass().getName() + "</br></br>");
			out.println("The request URI: " + requestUri + "<br><br>");
			out.println("The exception message: " + throwable.getMessage());
		}
		out.println("</body>");
		out.println("</html>");
	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}

//package com.revature.webstore.controllers;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.revature.webstore.utils.Logger;
//
//public class ErrorHandler extends HttpServlet {
//	Logger logger = Logger.getInstance();
//
//	String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
//			+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text" + "/html");
//		
//		try (PrintWriter out = resp.getWriter()) {
//			out.println(buildPage404());
//			// LOGGER
//			logger.log(Logger.LogLevel.fatal, "Page 404");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private String buildPage404() {
//		return "<!DOCTYPE html>" + "<html>" + "<head><title>Register Page</title>" + cssTag + "</head>" + "<body>"
//		// HEADER
//				+ "<div class=\"header\">" + "<h1>Welcome to WebStore!</h1>" + "<p>With a <b>flexible</b> layout.</p>"
//				+ "</div>"
//				// END OF HEADER
//
//				// NAVBAR
//				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
//				+ "<div class=\"topnav topnav_position_right\">"
//				// END NAVBAR
//
//				// MAIN CONTENT
//				+ "<h1 style=\"text-align: center\">404</h1>"
//				// END OF MAIN CONTENT
//
//				// FOOTER
//				+ "</br>" + "<div class=\"footer\">"
//				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</div>"
//				// END OF FOOTER
//
//				// EOF
//				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
//	}
//}
