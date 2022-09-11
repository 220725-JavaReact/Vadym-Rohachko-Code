package com.revature.webstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String cssTag = "<link rel='stylesheet' type='text/css' HREF='css/login.css'>";
		resp.setStatus(200);
		try (PrintWriter out = resp.getWriter()) {
			out.write("<!DOCTYPE html>" + "<html>" + "<head><title>OnlineStore</title>" + cssTag + "</head>" + "<body>"
					+ "<form action=\"action_page.php\" method=\"post\">" + "<div class=\"imgcontainer\">"
					+ "<p>LOGIN</p>"
					//+ "<img src=\"img_avatar2.png\" alt=\"Avatar\" class=\"avatar\">" + "</div>"					

					+ "<div class=\"container\">" + " <label for=\"uname\"><b>Username</b></label>"
					+ "<input type=\"text\" placeholder=\"Enter Username\" name=\"uname\" required>"

					+ "<label for=\"psw\"><b>Password</b></label>"
					+ " <input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>"

					+ " <button type=\"submit\">Login</button>" + " <label>"
					+ "  <input type=\"checkbox\" checked=\"checked\" name=\"remember\"> Remember me" + " </label>"
					+ " </div>"

					+ " <div class=\"container\" style=\"background-color:#f1f1f1\"> "
					+ "  <button type=\"button\" class=\"cancelbtn\">Cancel</button>"
					+ "  <span class=\"psw\">Forgot <a href=\"#\">password?</a></span>" + " </div>" + "</form> "
					+ "</body>" + "</html>");
		}

	}

}
