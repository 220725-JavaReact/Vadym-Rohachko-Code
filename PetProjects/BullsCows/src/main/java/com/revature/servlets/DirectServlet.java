package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dl.DAO;
import com.revature.dl.wordDAO;
import com.revature.models.*;

public class DirectServlet extends HttpServlet {
	int newInt = 0;

	private static DAO<word> wordDao = new wordDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String newWord = req.getParameter("Word");
		word brandNewWord = new word(newWord.length(), newWord);
		wordDao.addInstance(brandNewWord);

		newInt += 4;
		System.out.println("Direct get");
		res.setContentType("text/html");
		res.getWriter().write("<!DOCTYPE html>");
		res.getWriter().write("<html><head><title>Input Servlet</title></head><body>");
		res.getWriter().write("<h1>The WORD: " + newWord + " The INT: " + newInt + "</h1>");
		res.getWriter().write("<form method = \"get\" action = \"/BullsCows/direct\">"
				+ "<input type = \"submit\" value = \"Direct Text Response\"/>" + "</form>");

		PrintWriter out = res.getWriter();
		out.println("<h1>");
		out.println("Hello " + newWord);
		out.println("</h1>");

		if (newInt > 100) {
			res.getWriter().write("<h1>The Servlet is talking directly to the client</h1>");
			res.getWriter().write("</body></html>");
			newInt = 0;
		}
	}
}
