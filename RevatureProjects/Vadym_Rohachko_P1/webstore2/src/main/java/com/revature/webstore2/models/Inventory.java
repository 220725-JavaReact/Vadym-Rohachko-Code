package com.revature.webstore2.models;

public class Inventory {

	private int productId;
	private int categoryId;
	private int manufacturerId;
	private String product;
	private String category;
	private int quantity;
	private int orderedQty;
	private double price;
	private String manufacturer;

	public Inventory() {
	}

	public Inventory(int productId, int categoryId) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
	}
	
	public Inventory(int productId, int categoryId, String category) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.category = category;
	}

	public Inventory(int productId, int categoryId, int manufacturerId, String product, String category, int quantity,
			int orderedQty, double price, String manufacturer) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.manufacturerId = manufacturerId;
		this.product = product;
		this.category = category;
		this.quantity = quantity;
		this.orderedQty = orderedQty;
		this.price = price;
		this.manufacturer = manufacturer;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(int orderedQty) {
		this.orderedQty = orderedQty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
