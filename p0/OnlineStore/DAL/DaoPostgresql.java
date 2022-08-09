package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Models.*;
import Util.ConnectionFactory;


public class DaoPostgresql implements IDao<User> {
    @Override
    public void addUser(User newInstance) {
		System.out.println("I am in DAO " + newInstance.getEmail());
        try {
			Connection connie = ConnectionFactory.getInstance().getConnection();
			System.out.println("Connection successful!");
//			String query = "Insert into teams (team_name, points) values (?,?)";
//			PreparedStatement pstmt = connie.prepareStatement(query);
//			pstmt.setString(1, newInstance.getEmail());
//			pstmt.setString(2, newInstance.getPass());
//			pstmt.execute();
		} catch(Exception e) {
			System.out.println("Error connection!");
		}
    }

	@Override
	public User checkUser(User user) {
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			String query = "select * from users where email = ? and pass = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPass());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new User(rs.getInt("user_id"), rs.getString("fname"), rs.getString("lname"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registerUser(User user) {
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			String query = "insert into users(fname, lname,email,pass) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFname());
			pstmt.setString(2, user.getLname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPass());

			pstmt.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//
//	@Override
//	public void addInstance(BLLManager newInstance) {
//		// TODO Auto-generated method stub
//		try {
//			Connection connie = ConnectionFactory.getInstance().getConnection();
//			System.out.println("Connection successful!");
//		} catch(Exception e) {
//			System.out.println("Error connection!");
//		}
//	}
//
//	@Override
//	public BLLManager getByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BLLManager[] getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateInstance(BLLManager updateInstance) {
//		// TODO Auto-generated method stub
//
//	}

}
