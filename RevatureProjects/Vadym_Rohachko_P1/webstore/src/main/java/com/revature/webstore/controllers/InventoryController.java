package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class InventoryController extends HttpServlet {
	Logger logger = Logger.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.log(Logger.LogLevel.info, "Inside InventoryController");
		HttpSession session = req.getSession(false);
		
		final String URI = req.getRequestURI().replace("/webstore/", "");
		switch (URI) {
		case "regist":
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "Reg page called");				
				
				String custEmail = (String) session.getAttribute("currCust");
				if(custEmail != null && String.valueOf(custEmail) != "") {
					Optional<CustomerSession> custSession;
					custSession = Optional.ofNullable((CustomerSession) session.getAttribute(custEmail)); 						
					if(custSession.isEmpty()) {   
						// Error
						logger.log(Logger.LogLevel.info, "InventoryController: failed to get customer's inventory");
						return;
					}	else {
						
						int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
						int productId = Integer.valueOf(req.getParameter("productId"));						
						
						InventoryService inventoryService = new InventoryService(new InventoryDao());
						List<Inventory> inventories = inventoryService.getAllInventories();
						Inventory inventory = inventories.stream()
								.filter(i -> (i.getCategoryId() == categoryId && i.getProductId() == productId)).findFirst().get();
						CustomerSession customerSession = custSession.get();
						inventory.setOrderedQty(1);
						customerSession.getCustomerInventory().add(inventory);
						// rewrite customer's inventory in session with new data
						session.setAttribute(custEmail, customerSession);
						logger.log(Logger.LogLevel.info, "InventoryController: new item added to customer's session");
					}
				} else {
					// Error
					logger.log(Logger.LogLevel.info, "InventoryController: failed to get customer's session as session attribute currCust is empty");
					return;
				}
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "InventoryController");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}			
			break;
		case "unregist":
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "Unreg page called");		
				
				Optional<List<Inventory>> uInventory;
				uInventory = Optional.ofNullable((List<Inventory>) session.getAttribute("unregInventoryKey")); 
				
				 if(uInventory.isEmpty()) {   
				   session.setAttribute("unregInventoryKey", new ArrayList<Inventory>());
				   uInventory = Optional.ofNullable((List<Inventory>) session.getAttribute("unregInventoryKey")); 
				 }	
				
				int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
				int productId = Integer.valueOf(req.getParameter("productId"));			
				
				List<Inventory> localInventory = uInventory.get();
				localInventory.add(new Inventory(categoryId, productId));
				// rewrite with new data
				session.setAttribute("unregInventoryKey", localInventory);
				logger.log(Logger.LogLevel.info, "size of unregInventory = " + localInventory.size());
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "InventoryController");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}
			break;
		default:
			// In the event that someone tried to access get request that isn't allowed
			// I've used the super method of doGet because that already returns a 405 method
			// not allowed in a proper way
			super.doGet(req, resp);
			break;
		}
	
				
	}
	
	/*
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try (PrintWriter out = resp.getWriter()) {
			HttpSession session = req.getSession();				
			List<Inventory> uInventory = (List<Inventory>) session.getAttribute("unregisteredInventoryKey");
			
			if(uInventory == null) {
				List<Inventory> unregisteredInventory = new ArrayList<Inventory>();
				session.setAttribute("unregisteredInventoryKey", unregisteredInventory);
				uInventory = (List<Inventory>) session.getAttribute("unregisteredInventoryKey");
			} 
			
			int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
			int productId = Integer.valueOf(req.getParameter("productId"));
			//logger.log(Logger.LogLevel.info, "categoryId = " + categoryId);
			//logger.log(Logger.LogLevel.info, "productId = " + productId);			
			
			uInventory.add(new Inventory(categoryId, productId));
			session.setAttribute("unregisteredInventoryKey", uInventory);
			logger.log(Logger.LogLevel.info, "size of unregisteredInventory = " + uInventory.size());
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}		
	}
	*/
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////








/*package com.revature.webstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.webstore.models.Inventory;
import com.revature.webstore.utils.Logger;

public class InventoryController extends HttpServlet {
	Logger logger = Logger.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.log(Logger.LogLevel.info, "Inside InventoryController");
		final String URI = req.getRequestURI().replace("/webstore/", "");
		switch (URI) {
		case "regist":
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "InventoryController regist called");		
				
				int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
				int productId = Integer.valueOf(req.getParameter("productId"));
				//logger.log(Logger.LogLevel.info, "categoryId = " + categoryId);
				//logger.log(Logger.LogLevel.info, "productId = " + productId);	
				
			} catch (Exception e) {				
				logger.log(Logger.LogLevel.error, "InventoryController registered customer");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}			
			break;
		case "unregist":
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "InventoryController unregist called");
				
				int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
				int productId = Integer.valueOf(req.getParameter("productId"));
				
				//logger.log(Logger.LogLevel.info, "categoryId = " + categoryId);
				//logger.log(Logger.LogLevel.info, "productId = " + productId);			
				
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "InventoryController unregistered customer");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}
			break;
		default:
			// In the event that someone tried to access get request that isn't allowed
			// I've used the super method of doGet because that already returns a 405 method
			// not allowed in a proper way
			super.doGet(req, resp);
			break;
		}
	
				
	}
	
}
*/









