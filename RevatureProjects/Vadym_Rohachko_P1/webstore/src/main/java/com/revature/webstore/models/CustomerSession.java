package com.revature.webstore.models;

import java.util.List;
public class CustomerSession {
	
	private String email;
	private String fName;
	private String lName;
	private List<Inventory> customerInventory;
	private boolean isLoggedIn;
	
	public CustomerSession(String email, String fName, String lName, List<Inventory> customerInventory, boolean isLoggedIn) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.customerInventory = customerInventory;
		this.isLoggedIn = isLoggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public List<Inventory> getCustomerInventory() {
		return customerInventory;
	}

	public void setCustomerInventory(List<Inventory> customerInventory) {
		this.customerInventory = customerInventory;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	
}

