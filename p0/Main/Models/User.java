package Models;

public class User {
	private int user_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public boolean isFull_access() {
		return full_access;
	}
	public void setFull_access(boolean full_access) {
		this.full_access = full_access;
	}
	
	private String lname;
	private String fname;
	private String email;
	private String pass;
	private String card_number;
	private boolean full_access;
	
	public String toString() {
		return "[lname = " + lname + " fname = " + fname + " ]";
	}
	

}
