package com.revature.webstore2.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.webstore2.dao.InventoryDao;
import com.revature.webstore2.dao.UserDao;
import com.revature.webstore2.models.CustomerSession;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.models.User;
import com.revature.webstore2.services.InventoryService;
import com.revature.webstore2.services.UserService;
import com.revature.webstore2.utils.Logger;

public class UserController extends HttpServlet {
	Logger logger = Logger.getInstance();
	
	private static UserService userService = new UserService(new UserDao());
	private static ObjectMapper objmap = new ObjectMapper();	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {			
			
			   final String URI = req.getRequestURI().replace("/webstore2/", "");
			   
		        resp.setContentType("application/json");
		        String jsonString;

		        switch (URI) {
		            case "user":
		                int id = 0;

		                try {
		                    id = Integer.parseInt(req.getParameter("id"));
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		                
		                User user = userService.getUser(id);
		                jsonString = objmap.writeValueAsString(user);
		                resp.getWriter().println(jsonString);

		                break;
		            case "alluser":
		                // resp.getWriter().println("I am getting multiple pokemon");
		                
		                //Displaying the result of our dao logic
		                List<User> users = userService.getUsers();

		                jsonString = objmap.writeValueAsString(users);
		                resp.getWriter().println(jsonString);

		                break;
		            default:
		                //In the event that someone tried to access get request that isn't allowed
		                //I've used the super method of doGet because that already returns a 405 method not allowed in a proper way
		                super.doGet(req, resp);
		                break;
		        }
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}



