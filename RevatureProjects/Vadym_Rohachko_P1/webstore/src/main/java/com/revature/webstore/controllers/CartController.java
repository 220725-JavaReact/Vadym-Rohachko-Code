package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore.dao.InventoryDao;
import com.revature.webstore.dao.UserDao;
import com.revature.webstore.models.CustomerSession;
import com.revature.webstore.models.Inventory;
import com.revature.webstore.models.User;
import com.revature.webstore.services.InventoryService;
import com.revature.webstore.services.UserService;
import com.revature.webstore.utils.Logger;

public class CartController extends HttpServlet {
	Logger logger = Logger.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {

			HttpSession session = req.getSession(false);
			 //out.print ( "<p>Session id = " + session.getId() + "</p>" );					
			
			//TEMP
			/*session.setAttribute("currCust", "z1@gmail.com");
			List<Inventory> inventory = new ArrayList<Inventory>();
			CustomerSession custSession = new CustomerSession("z1@gmail.com", "name","surname", inventory, false);
			session.setAttribute("z1@gmail.com", custSession);
			session.setAttribute("currCust", "");*/
			
			String custEmail = (String) session.getAttribute("currCust");
			
			if(custEmail != null && custEmail != "") {				
					logger.log(Logger.LogLevel.info, "Start processing registered cart...");
					Optional<CustomerSession> customerSession = Optional.ofNullable((CustomerSession) session.getAttribute(custEmail));
					if(customerSession != null && customerSession.isPresent()) {
						String customerName = customerSession.get().getfName() + " " + customerSession.get().getfName();				   
												
						List<Inventory> inventories = customerSession.get().getCustomerInventory();						
											
						String cards = "";
						for (Inventory inventory : inventories) {
							String card = "<form>"
									+ "<label>Category = " 
									+ inventory.getCategory() 
									+ " Product = " 
									+ inventory.getProduct() 
									+ " Price = "
									+ inventory.getPrice()
									+ " Qty = "
									+ inventory.getOrderedQty()
									+ "</label>"
									+ "<button>Add</button>"
									+ "<button>Remove</button>"
									+ "</form>";
							cards += card;
						}					
						
						out.println(buildStorePage(customerName, cards));
						
					} else {
						logger.log(Logger.LogLevel.info, "Start processing unregistered cart...");
						// cart of unregistered customer
						out.println(buildStorePage("_Unregistered customer", ""));
					}									 
			} else {
				logger.log(Logger.LogLevel.info, "Start processing unregistered cart...");
				// cart of unregistered customer
				out.println(buildStorePage("Unregistered customer_", ""));
			}	

			logger.log(Logger.LogLevel.info, "Cart loaded");
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "_CartController: Error in getting isLoggedInKey");
			logger.log(Logger.LogLevel.info, e.getMessage());
			e.printStackTrace();
		}
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private String buildStorePage(String customer, String inventory) {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Page</title>" + cssTag + "</head>"
				+ "<body>"
      		    // HEADER
				+ "<header class=\"header\">" + "<h1>Shopping Cart</h1>" 
                + "<p>"
				// NAVBAR
				+ customer +  "</p></header><div class=\"topnav\">" 
				+ "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"active topnav__link\" href=\"/webstore/payment\">Pay here</a>"
				+ "</div>" + "</div>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<div class=\"main-container\">" 
				//SECTION (right column)
				+ "<section class=\"content\">"
		        // END OF SECTION
				+ inventory
		        + "</section>"
				// LEFT SIDE MENU
				+ "<div class=\"side\">" 				
				+ "</div>" 
				// END LEFT SIDE MENU
				
				// END OF MAIN CONTENT (right column)
				+ "</div>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<footer class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</footer>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
	
	private String cartIsEmpty() {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Page</title>" + cssTag + "</head>"
				+ "<body>"
      		    // HEADER
				+ "<header class=\"header\">" + "<h1>Shopping Cart</h1>" 
                + "<p>"
				// NAVBAR
				+ "</p></header><div class=\"topnav\">" 
				+ "<a class=\"active topnav__link\" href=\"/webstore/store\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"active topnav__link\" href=\"/webstore/payment\">Pay here</a>"
				+ "</div>" + "</div>"
				// END NAVBAR
				
				+ "<h3 style=\"text-align:center\">Cart is empty</h3>"

				// FOOTER
				+ "</br>" + "<footer class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</footer>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
}
