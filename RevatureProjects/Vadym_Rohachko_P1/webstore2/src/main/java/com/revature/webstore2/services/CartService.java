package com.revature.webstore2.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revature.webstore2.dao.UserDao;
import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.Cart;
import com.revature.webstore2.models.User;
import com.revature.webstore2.utils.Logger;

public class CartService {
	Logger logger = Logger.getInstance();
	private Dao<Cart> cartDao;
	
	public CartService(Dao<Cart> cartDao) {
		this.cartDao = cartDao;
	}
	
	private List<Cart> getAllCarts() {
		List<Cart> carts = cartDao.getAllInstance();
		return carts;
	}
	
	public void removeSingleRecordFromUserCart(Cart cart) {
		this.cartDao.deleteInstance(cart);
	}

	public List<Cart> getCartsByUserId(int userId) {
		List<Cart> carts = this.getAllCarts();
		// List<Cart> cartsById = carts.stream()
		// .filter(cart -> cart.getUserId() == userId).collect(Collectors.toList());
		List<Cart> cartsById = new ArrayList();
		
		for(Cart cart : carts) {
			if(cart.getUserId() == userId) {
				cartsById.add(cart);
			}
		}
				
		if (cartsById == null || cartsById.size() == 0) {
			//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Timestamp instant = Timestamp.from(Instant.now());
			cartsById.add(new Cart(0,0,0,0,0,0,"","","No inventory found",0,0.00,"","",0, instant));
			return carts;
		}		
		return cartsById;
	}
	
	public void addCart(Cart cart) {
		cartDao.addInstance(cart);
		logger.log(Logger.LogLevel.info, "CartService: inserted");
	}
	
	public void modifyCart(Cart cart) {
		List<Cart> carts =  this.getAllCarts();			
		
		if(carts.get(0).getUserId() == 0) {
			cartDao.addInstance(cart);
			logger.log(Logger.LogLevel.info, "CartService: inserted");
		}else {			
			Optional<Cart> tempCart = carts.stream()
					.filter(i -> i.getCategoryId() == cart.getCategoryId() 
					&& i.getProductId() == cart.getProductId()).findFirst();		
			
			if(tempCart.isPresent()) {
				Cart cartToUpdate = tempCart.get();
				
				if(cart.getFname() != null && cart.getFname() != "" && !cartToUpdate.getFname().equals(cart.getFname())) {
					cartToUpdate.setFname(cart.getFname());
				}
				
				if(cart.getLname() != null && cart.getLname() != "" && !cartToUpdate.getLname().equals(cart.getLname())) {
					cartToUpdate.setLname(cart.getLname());	
				}
				
				//if(cartToUpdate.getAvailableQty() != cart.getAvailableQty()) {
			    //   cartToUpdate.setAvailableQty(cart.getAvailableQty());
				//}
				
				if(cartToUpdate.getQuantity() + cart.getQuantity() <= 0) {
					cartDao.deleteInstance(cartToUpdate);
				} else {
					cartToUpdate.setQuantity(cartToUpdate.getQuantity() + cart.getQuantity());
				}						
				
				cartDao.updateInstance(cartToUpdate);
				logger.log(Logger.LogLevel.info, "CartService: updated");
			} else {
				cartDao.addInstance(cart);
				logger.log(Logger.LogLevel.info, "CartService: inserted");
			}
		}	
	}	
}
