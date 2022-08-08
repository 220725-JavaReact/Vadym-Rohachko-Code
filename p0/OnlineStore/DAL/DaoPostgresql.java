package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.ConnectionFactory;
import Models.BLLManager;

public class DaoPostgresql implements DAO<BLLManager> {

	@Override
	public void addInstance(BLLManager newInstance) {
		// TODO Auto-generated method stub
		try {
			Connection connie = ConnectionFactory.getInstance().getConnection();
			System.out.println("Connection successful!");
		} catch(Exception e) {
			System.out.println("Error connection!");
		}
	}

	@Override
	public BLLManager getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BLLManager[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInstance(BLLManager updateInstance) {
		// TODO Auto-generated method stub
		
	}

}
