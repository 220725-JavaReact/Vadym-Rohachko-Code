package com.revature.webstore2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

import com.revature.webstore2.interfaces.Dao;
import com.revature.webstore2.models.User;
import com.revature.webstore2.utils.ConnectionUtil;

public class UserDao implements Dao<User> {

	@Override
	public void addInstance(User instance) {
		String sql = "insert into Users(fname, lname, email, pass) values(?,?,?,?) returning user_id";
		try (Connection con = ConnectionUtil.getConnection()) {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, instance.getfName());
			stmt.setString(2, instance.getlName());
			stmt.setString(3, instance.getEmail());
			stmt.setString(4, instance.getPass());

			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllInstance() {
		List<User> users = new ArrayList();
		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				users.add(new User(rs.getInt("user_id"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("email"), 
						rs.getString("pass"), 
						rs.getTimestamp("created_at"), 
						rs.getBoolean("isLoggedin"),
						rs.getString("session_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (users.size() == 0) {
			Timestamp instant = Timestamp.from(Instant.now());
			users.add(new User(0, "No user found", "", "", "", instant));
		}
		return users;
	}

	@Override
	public void updateInstance(User instance) {
		// String sql = "update users set isLoggedin = true where email = ?";
		String sql = "update users set  fname = ?, lname = ?, pass = ?, isLoggedin = ?, session_id = ? where email = ?";
		try (Connection con = ConnectionUtil.getConnection()) {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, instance.getfName());
			stmt.setString(2, instance.getlName());
			stmt.setString(3, instance.getPass());
			stmt.setBoolean(4, instance.getCurrent());
			stmt.setString(5, instance.getSessionId());
			stmt.setString(6, instance.getEmail());

			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInstance(User instance) {
		// TODO Auto-generated method stub

	}
/*
	public void logout() {
		String sql = "update users set isLoggedin = false where isLoggedin = true";
		try (Connection con = ConnectionUtil.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login(String email) {
		String sql = "update users set isLoggedin = true where email = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}

