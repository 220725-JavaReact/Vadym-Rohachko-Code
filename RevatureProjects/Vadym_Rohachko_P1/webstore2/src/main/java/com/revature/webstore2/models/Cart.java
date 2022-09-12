package com.revature.webstore2.models;

import java.sql.Timestamp;

public class Cart {
	private int cartId;
	private int userId;
	private int itemId;
	private int productId;
	private int categoryId;
	private int manufacturerId;
	private String fname;
	private String lname;
	private String product;
	private int quantity;
	private double pricePerItem ;
	private String category;
	private String manufacturer;
	private int availableQty;
	private Timestamp createdAt ;
	
	public Cart(int userId , int categoryId,  int productId ) {
		super();
		this.userId = userId;
		this.categoryId = categoryId;
		this.productId = productId;
	}
	
	public Cart(int userId , int categoryId,  int productId, int quantity ) {
		super();
		this.userId = userId;
		this.categoryId = categoryId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public Cart(int cartId, int userId, int itemId, int productId, int categoryId, int manufacturerId, String fname,
			String lname, String product, int quantity, double pricePerItem, String category, String manufacturer,
			int availableQty, Timestamp createdAt) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.itemId = itemId;
		this.productId = productId;
		this.categoryId = categoryId;
		this.manufacturerId = manufacturerId;
		this.fname = fname;
		this.lname = lname;
		this.product = product;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
		this.category = category;
		this.manufacturer = manufacturer;
		this.availableQty = availableQty;
		this.createdAt = createdAt;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
}
