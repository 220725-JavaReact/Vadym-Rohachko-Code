package com.revature.webstore2.services;

import java.util.List;

import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.Inventory;

public class InventoryService {
	private Dao<Inventory> inventoryDao;

	public InventoryService(Dao<Inventory> inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public List<Inventory> getAllInventories() {
		List<Inventory> inventories = inventoryDao.getAllInstance();
		return inventories;
	}
	
	public boolean placeItemIntoShoppingCart(Inventory inventory) {
		/*
		List<Inventory> inventories = inventoryDao.getAllInstance();
		Inventory invent = inventories.stream()
		//.peek(num -> System.out.println("will filter " + num))
		.filter(item -> item.getCategoryId() == inventory.getCategoryId() 
		             && item.getProductId() == inventory.getProductId())
		.findFirst()
		.get();
		
		if(invent) {
			//update
			this.inventoryDao.updateInstance(inventory);
		} else {
			//insert
			this.inventoryDao.addInstance(inventory);
		}		
		
		inventories = inventoryDao.getAllInstance();
		invent = inventories.stream()
		//.peek(num -> System.out.println("will filter " + num))
		.filter(item -> item.getCategoryId() == inventory.getCategoryId() 
		             && item.getProductId() == inventory.getProductId())
		.findFirst()
		.get();
		
		if(invent != null) {
			return true;
		}
		*/
		return false;
		
	}		

}
