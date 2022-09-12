package com.revature.webstore2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.utils.ConnectionUtil;
import com.revature.webstore2.utils.Logger;

public class InventoryDao implements Dao<Inventory> {
	Logger logger = Logger.getInstance();

	@Override
	public void addInstance(Inventory instance) {
		try (Connection con = ConnectionUtil.getConnection()) {	
			String sql = "Insert into inventories ("
					+ "product_id, "
					+ "category_id, "
					+ "manufacturer_id, "
					+ "product, "
					+ "category, "
					+ "quantity, "
					+ "price, "
					+ "manufacturer )";
		} catch(Exception e) {
			logger.log(Logger.LogLevel.error, "InventoryDao");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Inventory> getAllInstance() {
		List<Inventory> inventories = new ArrayList();
		try (Connection con = ConnectionUtil.getConnection()) {		
			String sql =
					"select "
					+ "inventories.product_id, "
					+ "inventories.category_id, "
					+ "manufacturers.manufacturer_id, "
					+ "products.product, "
					+ "categories.category, "
					+ "inventories.quantity, "
					+ "products.price, "
					+ "manufacturers.manufacturer "
					+ "from inventories "
					+ "inner join Categories "
					+ "on categories.category_id = inventories.category_id "
					+ "inner join products "
					+ "on inventories.product_id = products.product_id "
					+ "inner join manufacturers "
					+ "on manufacturers.manufacturer_id = products.manufacturer_id";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				inventories.add(new Inventory(
						rs.getInt("product_id"),
						rs.getInt("category_id"),
						rs.getInt("manufacturer_id"),
						rs.getString("product"),
						rs.getString("category"),
						rs.getInt("quantity"),
						0,
						rs.getDouble("price"),
						rs.getString("manufacturer")));
			}
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "InventoryDao");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}
		if(inventories.size() == 0) {
			inventories.add(new Inventory(0,0,0,"No inventory found","",0,0,0.00,""));
		}
		return inventories;
	}

	@Override
	public void updateInstance(Inventory instance) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void deleteInstance(Inventory instance) {
				//asd
	}

}

