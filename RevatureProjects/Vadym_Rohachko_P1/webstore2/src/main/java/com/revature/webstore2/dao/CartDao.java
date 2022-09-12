package com.revature.webstore2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.Cart;
import com.revature.webstore2.models.Inventory;
import com.revature.webstore2.models.User;
import com.revature.webstore2.utils.ConnectionUtil;
import com.revature.webstore2.utils.Logger;

public class CartDao implements Dao<Cart> {
	Logger logger = Logger.getInstance();
	
	

	@Override
	public List<Cart> getAllInstance() {
		
		List<Cart> carts = new ArrayList();
		try (Connection con = ConnectionUtil.getConnection()) {		
			String sql = "select * from carts";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				carts.add(new Cart(
						rs.getInt("cart_id"),
						rs.getInt("user_id"),
						rs.getInt("item_id"),
						rs.getInt("product_id"),
						rs.getInt("category_id"),
						rs.getInt("manufacturer_id"),
						rs.getString("fname"),
						rs.getString("lname"),
						rs.getString("product"),
						rs.getInt("quantity"),
						rs.getDouble("price_per_item"),
						rs.getString("category"),
						rs.getString("manufacturer"),
						rs.getInt("available_qty"),
						rs.getTimestamp("created_at")));
			}
		} catch (Exception e) {
			logger.log(Logger.LogLevel.error, "CartDao ustom Exception thrown");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}
		if(carts.size() == 0) {
			Timestamp instant = Timestamp.from(Instant.now());
			carts.add(new Cart(0,0,0,0,0,0,"","","No inventory found",0,0.00,"","",0, instant));
		}
		return carts;
	}
	
	
	@Override
	public void addInstance(Cart instance) {
		try (Connection conn = ConnectionUtil.getConnection()) {	
			String sql = "insert into carts ("
					+ "user_id, "
					+ "fname, "
					+ "lname,  "
					+ "product_id, "
					+ "category_id, "
					+ "manufacturer_id, "
					+ "product, "
					+ "quantity, "
					+ "available_qty, "
					+ "price_per_item,  "
					+ "category, "
					+ "manufacturer)  "
					+ "with  t1 as( select "
					+ "user_id, "
					+ "fname, "
					+ "lname "
					+ "from "
					+ "users where user_id = ? ), "
					+ "t2 as( select "
					+ "inventories.product_id, "
					+ "inventories.category_id, "
					+ "manufacturers.manufacturer_id, "
					+ "products.product, "
					+ "? , "
					+ "inventories.quantity , "
					+ "products.price, "
					+ "categories.category, "
					+ "manufacturers.manufacturer "
					+ "from inventories inner join Categories "
					+ "on categories.category_id = inventories.category_id inner join products "
					+ "on inventories.product_id = products.product_id inner join manufacturers "
					+ "on manufacturers.manufacturer_id = products.manufacturer_id "
					+ "where inventories.category_id = ? and  inventories.product_id = ? ) "
					+ "select * from t1,t2";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, instance.getUserId());
			stmt.setInt(2, instance.getQuantity());
			stmt.setInt(3, instance.getCategoryId());
			stmt.setInt(4, instance.getProductId());
						
			stmt.executeUpdate();
			
			logger.log(Logger.LogLevel.info, "CartDao [Insert:Success]");
		} catch(Exception e) {
			logger.log(Logger.LogLevel.error, "CartDao [Insert:Failure]");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}		
	}

	@Override
	public void updateInstance(Cart instance) {
		try (Connection conn = ConnectionUtil.getConnection()) {	
			
			String sql = "update carts set "
					+ "fname = ?, "
					+ "lname = ?, "
					+ "quantity = ?, "
					+ "available_qty = ? "					
					+ "where category_id = ? "
					+ "and product_id = ? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);			

			stmt.setString(1, instance.getFname());
			stmt.setString(2, instance.getLname());
			stmt.setInt(3, instance.getQuantity());
			stmt.setInt(4, instance.getAvailableQty());	
			stmt.setInt(5, instance.getCategoryId());
			stmt.setInt(6, instance.getProductId());
			
			stmt.executeUpdate();
			
			logger.log(Logger.LogLevel.info, "CartDao [Update:Success]");
		} catch(Exception e) {
			logger.log(Logger.LogLevel.error, "CartDao [Update:Failre]");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}			
	}

	@Override
	public void deleteInstance(Cart instance) {
		try (Connection conn = ConnectionUtil.getConnection()) {	
			
			String sql = "delete from carts "					
					+ "where category_id = ? "
					+ "and product_id = ? "
					+ "and user_id = ? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);			

			stmt.setInt(1, instance.getCategoryId());
			stmt.setInt(2, instance.getProductId());
			stmt.setInt(3, instance.getUserId());	
						
			stmt.executeUpdate();
			
			logger.log(Logger.LogLevel.info, "CartDao [Delete:Succsess]");
		} catch(Exception e) {
			logger.log(Logger.LogLevel.error, "CartDao [Delete:Failure]");
			logger.log(Logger.LogLevel.error, e.getMessage());
			e.printStackTrace();
		}	
		
	}
}


