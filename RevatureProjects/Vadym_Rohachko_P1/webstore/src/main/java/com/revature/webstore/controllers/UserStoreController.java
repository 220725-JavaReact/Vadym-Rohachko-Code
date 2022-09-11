package com.revature.webstore.controllers;

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

import com.revature.webstore.dao.InventoryDao;
import com.revature.webstore.models.CustomerSession;
import com.revature.webstore.models.Inventory;
import com.revature.webstore.services.InventoryService;
import com.revature.webstore.utils.Logger;

public class UserStoreController extends HttpServlet {
	Logger logger = Logger.getInstance();
	InventoryService inventoryService = new InventoryService(new InventoryDao());
	List<Inventory> inventories = inventoryService.getAllInventories();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			
			HttpSession session = req.getSession();
			
//			for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); ) 
//			{
//			     String attribName = (String) e.nextElement();
//			     Object attribValue = session.getAttribute(attribName);			
//			    out.println("<p> attribName = " + attribValue + "</p>" );
//			} 
			
			String loggedCustomer =  (String) session.getAttribute("currCust");

			if (loggedCustomer != null && String.valueOf(loggedCustomer) != "") {
				// registered customer page
				String currCust = loggedCustomer;
				if (currCust != "") {
					// customer's cart
					Optional<CustomerSession> customerSession = Optional
							.ofNullable((CustomerSession) session.getAttribute(currCust));
					String customerName = "";
					if (customerSession.isPresent()) {
						customerName = customerSession.get().getfName() + " " + customerSession.get().getlName();
					}
					out.println(buildStorePage(customerName, "reg"));
					logger.log(Logger.LogLevel.info,
							"UserStoreController: successfully logged onto store page for registered customers");
				} else {
					logger.log(Logger.LogLevel.error, "UseStoreController: attribute curCust is empty!");
					// resp.sendRedirect("/webstore/");
					// return;
				}
			} else {
				// unreg customer page
				out.println(buildStorePage("Unreg customer", "unreg"));
			}
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "UseStoreController");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}
		// super.doGet(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private String buildStorePage(String customerName, String regUnregCard) {
		String part2 = "";
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">";

		String part1 = "<!DOCTYPE html>" + "<html>" + "<head> " + "<script src='js/jquery_ajax.js'></script>"
				+ "<title>Shopping Cart</title>" + cssTag + "</head>" + "<body>"
//      		// HEADER
				+ "<header class=\"header\">" + "<h1>Let's Shopping!</h1><p>[ "
				+ customerName + " ]</p>" + "</header>"
				// END OF HEADER

				// <!-- Navigation Bar -->
				+ "<div class=\"topnav\">" + "<a class=\"active topnav__link\" >Home</a>"
				+ "<a class=\"topnav__link\" href=\"#news\">News</a>"
				+ "<a class=\"topnav__link\" href=\"#contact\">Contact</a>" + "<div class=\"dropdown\">"
				+ "<button class=\"dropdown__dropbtn\">Categories" + "<i class=\"fa fa-caret-down\"></i>" + "</button>"
				+ "<div class=\"dropdown__content\">" + "<a class=\"topnav__link dropdown__link\" href=\"#\">Music</a>"
				+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Soft</a>"
				+ "<a class=\"topnav__link dropdown__link\" href=\"#\">Games</a>" + "</div>" + "</div>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"topnav__link \" href=\"/webstore/cart\">Cart</a>";
				
				String regUnregMenu = "";
				if(regUnregCard == "reg") {
					regUnregMenu = "<a class=\"topnav__link \" href=\"/webstore/logout\">Logout</a>" ;
				} else {
					regUnregMenu = "<a class=\"topnav__link \" href=\"/webstore/reg\">Register</a>" 
					+ "<a class=\"topnav__link \" href=\"/webstore/log\">Login</a>" ;
				}				
				
				String part1_1 = "</div>" 
				+ "</div>"
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

		// END OF MAIN SECTION
		String part3 = "</section>"
				// END OF MAIN CONTENT (right column)
				+ "</div>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "</br>" + "<footer class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</footer>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "<script src=\"js/ajax_servlet.js\"></script>" + "</body>"
				+ "</html>";

		for (Inventory inventory : inventories) {
			// PRODUCT CARD
			String card = ""
					+ "<form  class=\"card\"  >" 
			        + "<img class=\"card__img\" src=\"\" alt=" + inventory.getCategory() + " />" 
			        + "<h3 class=\"card__title\">" + inventory.getProduct() + "</h3>"
					+ "<h5 class=\"card__title\">" + inventory.getManufacturer() + "</h5>" 
			        + "<p class=\"card__price\">"
					+ inventory.getPrice() + "</p>" + "<p class=\"card__desc\">"
					+ "Some text about the jeans. Super slim and comfy lorem ipsum lorem jeansum. "
					+ "Lorem jeamsun denim lorem jeansum." + "</p>"
					+ "<p><button name=\"" + regUnregCard + "\" type=\"submit\" class=\"card__button card__button_type_submit\">Add to Cart</button></p>"
					+ "<input class=\"card__product\" type=\"text\"  name=\"productId\" value="
					+ inventory.getProductId() + " hidden>"
					+ "<input class=\"card__category\" type=\"text\"  name=\"categoryId\" value="
					+ inventory.getCategoryId() + " hidden>" + "<p hidden>" + inventory.getCategoryId() + "</p>"
					+ "</form>";
			// END OF PRODUCT CARD
			part2 += card;
		}

		return part1 + regUnregMenu + part1_1 + part2 + part3;
	}
}
