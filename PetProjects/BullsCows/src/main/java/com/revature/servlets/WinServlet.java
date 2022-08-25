package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dl.DAO;
import com.revature.dl.GuessDAO;
import com.revature.dl.wordDAO;
import com.revature.models.Guess;
import com.revature.models.word;

public class WinServlet extends HttpServlet{
	private static DAO<word> wordDao = new wordDAO();
	private static DAO<Guess> guessDao = new GuessDAO();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>"); 
        out.println("<title>Input Servlet</title>"); 
        out.println("</head>"); 
        out.println("<body>"); 
        out.println("<h1>WELL DONE!</h1>");          
        
        //for loop print guesses, bulls, and cows
        out.println("<label>GUESS___</label><label>BULLS___</label><label>COWS___</label>");
        ArrayList<Guess> guesses = guessDao.getAllInstances();
        if(guesses.size() != 0)
		{
			for(Guess thisGuess: guessDao.getAllInstances())
			{
				out.println("<br><label>GUESS: " + thisGuess.getGuessName() + " BULLS: " + thisGuess.getBulls() + " COWS: "+ thisGuess.getCows() +"</label>");
			}
		}
        //the secret word was: secret word     
        out.println("<h1>THE SECRET WORD WAS " + wordDao.findSecretWord().getWordName() + "</h1>");
        
        //set new secret word
        wordDao.unMarkSecretWord();        
        
        //play again button, home button
        out.println("<form method = \"get\" action = \"/BullsCows/play\">");		
		out.println("<input type = \"submit\" value = \"PLAY AGAIN\">");		
		out.println("</form>");
        
        guessDao.truncateTable();
	}
}
