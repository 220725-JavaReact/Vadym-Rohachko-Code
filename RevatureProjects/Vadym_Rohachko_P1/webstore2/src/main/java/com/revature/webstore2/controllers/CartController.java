package com.revature.webstore2.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore2.dao.CartDao;
import com.revature.webstore2.dao.InventoryDao;
import com.revature.webstore2.dao.UserDao;
import com.revature.webstore2.models.Cart;
import com.revature.webstore2.models.CustomerSession;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.models.User;
import com.revature.webstore2.services.CartService;
import com.revature.webstore2.services.InventoryService;
import com.revature.webstore2.services.UserService;
import com.revature.webstore2.utils.Logger;

public class CartController  extends HttpServlet {
	Logger logger = Logger.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {
			UserService userService = new UserService(new UserDao());
			User user = userService.getCurrentUser();
			CartService cartService = new CartService(new CartDao());
			List<Cart> carts = cartService.getCartsByUserId(user.getUserId());
			
			String customerName = "Unknown";
			String cards = "";
			double grandTotal = 0;
			DecimalFormat df = new DecimalFormat("0.00");
			
			if(carts != null && carts.size() != 0 && carts.get(0).getUserId() == user.getUserId()) {				
					logger.log(Logger.LogLevel.info, "Start processing registered cart...doGet()");	
					customerName = carts.get(0).getFname() + " " + carts.get(0).getLname();											
						
					String header = "<form class=\"wrapper-line wrapper-line_header\">"
							+ "<div class=\"wrapper-line__text-panel\">"
							+ "<p class=\"wrapper-line__product\">Product</p>" 
							+ "<p class=\"wrapper-line__category\">Category</p>"
							+ "<p class=\"wrapper-line__manufacturer\">Manufacturer</p>"
							+ "</div>"
							+ "<div class=\"wrapper-line__control-panel\">"
							+ "<p class=\"wrapper-line__price wrapper-line_width_40\">Price</p>"
							+ "<p class=\"wrapper-line__quantity wrapper-line_width_50\">Qty</p>"
							+ "<p class=\"wrapper-line__total wrapper-line_width_40\">Total</p>"
							+ "</div>"
							+ "</form>";
					
						for (Cart cart : carts) {
							String card = "<form class=\"wrapper-line\">"
									+ "<div class=\"wrapper-line__text-panel\">"
									+ "<p class=\"wrapper-line__product\">" + cart.getProduct() +"</p>" 
									+ "<p class=\"wrapper-line__category\">" + cart.getCategory() + "</p>"
									+ "<p class=\"wrapper-line__manufacturer\">" + cart.getManufacturer() + "</p>"
									+ "</div>"
									+ "<div class=\"wrapper-line__control-panel\">"
									+ "<p class=\"wrapper-line__price\">" +  cart.getPricePerItem() + "</p>"
									+ "<input data-product-id=" + cart.getProductId() + " data-category-id=" +  cart.getCategoryId() +  " class=\"wrapper-line__data-change wrapper-line__data-change_color_red\" type=\"button\" value=\"-\">"
									+ "<p class=\"wrapper-line__quantity\">" + cart.getQuantity()  +  "</p>"
									+ "<input data-product-id=" + cart.getProductId() + " data-category-id=" +  cart.getCategoryId() +  "  class=\"wrapper-line__data-change wrapper-line__data-change_color_green\" type=\"button\" value=\"+\">"
									+ "<p class=\"wrapper-line__total\">" 
									+ getTotal(cart.getPricePerItem(), cart.getQuantity())									
									+ "</p>" 
									+ "</div>"
									+ "</form>";	
							
							cards += card;
							grandTotal += Double.valueOf(getTotal(cart.getPricePerItem(), cart.getQuantity()));
						}
						cards = header + cards;
						
						cards += "<div class=\"wrapper-line__grand-total\"><span>Total: </span><span class=\"wrapper-line__grand-total_text\">"  + df.format(grandTotal)  +  "</span></div>";
						out.println(buildStorePage(customerName, cards));
						logger.log(Logger.LogLevel.info, "Cart loaded doGet()");
			} else {	
				
				//out.println(buildStorePage(customerName, cards));
				out.println(cartIsEmpty());
				logger.log(Logger.LogLevel.info, "Cart is empty");
			}			
			
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "_CartController");
			logger.log(Logger.LogLevel.info, e.getMessage());
			e.printStackTrace();
		}
		//super.doGet(req, resp);
	}
	
	private String getTotal(double price, int qty) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(price *  qty);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "Cart page loaded doPost()");						
						int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
						int productId = Integer.valueOf(req.getParameter("productId"));	
						int orderedQty = Integer.valueOf(req.getParameter("orderedQty"));
						
						UserService userService = new UserService(new UserDao());
						User user = userService.getCurrentUser();
						CartService cartService = new CartService(new CartDao());
						Cart cart = new Cart(user.getUserId(), categoryId, productId, orderedQty);
						cartService.modifyCart(cart);
						
						logger.log(Logger.LogLevel.info, "CartController: modified");						
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "CartController");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}				
	}
	
	
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			logger.log(Logger.LogLevel.info, "Cart page loaded doDelete()");						
					int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
					int productId = Integer.valueOf(req.getParameter("productId"));	
					
					logger.log(Logger.LogLevel.info, "CartController: deleted categoryId " + categoryId);
					
					UserService userService = new UserService(new UserDao());
					User user = userService.getCurrentUser();
					CartService cartService = new CartService(new CartDao());
					Cart cart = new Cart(user.getUserId(), categoryId, productId);
					cartService.removeSingleRecordFromUserCart(cart);
					
					logger.log(Logger.LogLevel.info, "CartController: deleted");
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "CartController [Delete]");
			logger.log(Logger.LogLevel.error, e.getMessage());				
			e.printStackTrace();
		}		
}	

	private String buildStorePage(String customer, String inventory) {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Page</title>" + cssTag + "</head>"
				+ "<body>"
      		    // HEADER
				+ "<header class=\"header\">" + "<h1>_Shopping Cart_</h1>" 
                + "<p>"
				// NAVBAR
				+ customer +  "</p></header><div class=\"topnav\">" 
				+ "<a class=\"active topnav__link\" href=\"/webstore2/shopping\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"active topnav__link\" href=\"/webstore2/payment\">Pay here</a>"
				+ "</div>" + "</div>"
				// END NAVBAR

				// MAIN CONTENT
				+ "<div class=\"content-section\">" 
				//SECTION (right column)
				//+ "<section class=\"content\">"
		        // END OF SECTION
				+ inventory
		        //+ "</section>"
				// LEFT SIDE MENU
				//+ "<div class=\"side\">" 				
				//+ "</div>" 
				// END LEFT SIDE MENU
				
				// END OF MAIN CONTENT (right column)
				+ "</div>"
				// END OF MAIN CONTENT

				// FOOTER
				+ "<footer class=\"footer\">"
				+ "<p>Copyright Revature Java-React © 2022. All rights reserved.</p>" + "</footer>"
				// END OF FOOTER

				// EOF
				+ "<script src=\"js/main.js\"></script>" + "</body>" + "</html>";
	}
	
	private String cartIsEmpty() {
		String cssTag = "<link rel='stylesheet' type='text/css' href='css/styles.css'>";

		return "<!DOCTYPE html>" + "<html>" + "<head><title>Shopping Page</title>" + cssTag + "</head>"
				+ "<body>"
      		    // HEADER
				+ "<header class=\"header\">" + "<h1>Shopping Cart</h1>" 
                + "<p>"
				// NAVBAR
				+ "</p></header><div class=\"topnav\">" 
				+ "<a class=\"active topnav__link\" href=\"/webstore2/shopping\">Home</a>"
				+ "<div class=\"topnav topnav_position_right\">"
				+ "<a class=\"active topnav__link\" href=\"/webstore2/payment\">Pay here</a>"
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