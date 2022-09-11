package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore.dao.InventoryDao;
import com.revature.webstore.dao.UserDao;
import com.revature.webstore.models.Inventory;
import com.revature.webstore.models.User;
import com.revature.webstore.services.InventoryService;
import com.revature.webstore.services.UserService;
import com.revature.webstore.utils.Logger;

public class UserStoreController extends HttpServlet {
	Logger logger = Logger.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("webstore_current_user");

			out.println(buildStorePage(user));
			// LOGGER
			logger.log(Logger.LogLevel.info, "Store page loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private String buildStorePage(User user) {
		String part2 = "";
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		String part1 = "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Cart</title>" + cssTag + "</head>"
				+ "<body>"
//      		// HEADER
				+ "<div class=\"header\">" + "<h1>Let's Shopping!</h1>" + "<p6> (servlet version)</p6>" +"<p>[ " + user.getfName() + " "
				+ user.getlName() + " ]</p>" + "</div>"
				// END OF HEADER

				// <!-- Navigation Bar -->
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" href=\"/webstore/\">Home</a>"
				+ "<a class=\"topnav__link\" href=\"#news\">News</a>"
				+ "<a class=\"topnav__link\" href=\"#contact\">Contact</a>" + "<div class=\"dropdown\">"
				+ "<button class=\"dropdown__dropbtn\">Categories" + "<i class=\"fa fa-caret-down\"></i>" + "</button>"
				+ "<div class=\"dropdown__content\">" + "<a class=\"topnav__link dropdown__link\" href=\"#\">Music</a>"
				+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Soft</a>"
				+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Games</a>" + "</div>" + "</div>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore/cart\">Cart</a>"
				+ "<a class=\"topnav__link \" href=\"/webstore/logout\">Logout</a>" + "</div>" + "</div>" + "</br>"
				// END NAV

				// MAIN CONTENT
				+ "<div class=\"main-container\">" + "<div class=\"side\">"
				// LEFT SIDE MENU
				+ "<div class=\"vertical-menu\">"
				+ "<a id=0 href=\"#\" class=\"vertical-menu__link vertical-menu__link_active \">All Products</a>"
				+ "<a id=1 href=\"#\" class=\"vertical-menu__link \">Music</a>"
				+ "<a id=2 href=\"#\" class=\"vertical-menu__link\">Soft</a>"
				+ "<a id=3 href=\"#\" class=\"vertical-menu__link\">Games</a>" + "</div>" + "</div>"
				// END LEFT SIDE MENU

				// MAIN CONTENT (right column)
				+ "<section class=\"content\">";

//				// PRODUCT CARD
//				+ "<div class=\"card\">"
//				+ "<img class=\"card__img\" src=\"/w3images/jeans3.jpg\" alt=\"Denim Jeans\" style=\"width:100%\">"
//				+ "<h3 class=\"card__title\">Tailored Jeans</h3>" + "<p class=\"card__price\">$19.99</p>"
//				+ "<p class=\"card__desc\">Some text about the jeans. Super slim and comfy  Lorem jeamsun denim lorem jeansum.</p>"
//				+ "<p><button class=\"card__button\">Add to Cart</button></p>" 
//				+ "<p hidden><%= inventory.getCategoryId() %></p>"
//				+ "</div>"
//				// END OF PRODUCT CARD

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

		InventoryService inventoryService = new InventoryService(new InventoryDao());
		List<Inventory> inventories = inventoryService.getAllInventories();
		
		for(Inventory inventory : inventories) { 
	        
	    	 String temp =  "<div class=\"card\">"
	          + "<img class=\"card__img\" src=\"\" alt=" + inventory.getCategory() + " />"        
	          + "<h3 class=\"card__title\">" + inventory.getProduct() + "</h3>"
	          + "<h5 class=\"card__title\">" + inventory.getManufacturer() + "</h5>"
	          + "<p class=\"card__price\">" + inventory.getPrice() + "</p>"
	          + "<p class=\"card__desc\">"
	          + "Some text about the jeans. Super slim and comfy lorem ipsum lorem jeansum. "
	          + "Lorem jeamsun denim lorem jeansum."
	          + "</p>"
	          + "<p><button class=\"card__button\">Add to Cart</button></p>"
	          + "<p hidden>" + inventory.getCategoryId() + "</p>"
	          + "</div>";
	    	 
	    	 part2 += temp;
	      }

//				// PRODUCT CARD
//				+ "<div class=\"card\">"
//				+ "<img class=\"card__img\" src=\"/w3images/jeans3.jpg\" alt=\"Denim Jeans\" style=\"width:100%\">"
//				+ "<h3 class=\"card__title\">Tailored Jeans</h3>" + "<p class=\"card__price\">$19.99</p>"
//				+ "<p class=\"card__desc\">Some text about the jeans. Super slim and comfy  Lorem jeamsun denim lorem jeansum.</p>"
//				+ "<p><button class=\"card__button\">Add to Cart</button></p>" 
//				+ "<p hidden><%= inventory.getCategoryId() %></p>"
//				+ "</div>"
//				// END OF PRODUCT CARD

		return part1 + part2 + part3;
	}
}
