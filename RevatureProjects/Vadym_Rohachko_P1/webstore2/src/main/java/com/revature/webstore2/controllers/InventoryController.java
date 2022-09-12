package com.revature.webstore2.controllers;

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

import com.revature.webstore2.dao.InventoryDao;
import com.revature.webstore2.models.CustomerSession;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.services.InventoryService;
import com.revature.webstore2.utils.Logger;

public class InventoryController extends HttpServlet {
	Logger logger = Logger.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.log(Logger.LogLevel.info, "Inside InventoryController");
		
		final String URI = req.getRequestURI().replace("/webstore2/", "");
		switch (URI) {
		case "regist":
			try (PrintWriter out = resp.getWriter()) {
				logger.log(Logger.LogLevel.info, "Cart page loaded");						
						int categoryId =  Integer.valueOf(req.getParameter("categoryId"));
						int productId = Integer.valueOf(req.getParameter("productId"));						
						
						InventoryService inventoryService = new InventoryService(new InventoryDao());
						List<Inventory> inventories = inventoryService.getAllInventories();
						
						Inventory inventory = inventories.stream()
								.filter(i -> (i.getCategoryId() == categoryId && i.getProductId() == productId)).findFirst().get();
						
						inventory.setOrderedQty(1);
						logger.log(Logger.LogLevel.info, "InventoryController: new item added to customer's session");
			} catch (Exception e) {
				logger.log(Logger.LogLevel.error, "InventoryController");
				logger.log(Logger.LogLevel.error, e.getMessage());				
				e.printStackTrace();
			}			
			break;
		default:
			super.doGet(req, resp);
			break;
		}				
	}
}