package com.revature.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Guess;
import com.revature.storage.GuessList;
import com.revature.util.ConnectionFactory;

public class GuessDAO implements DAO<Guess>{

	@Override
	public void addInstance(Guess newInstance) {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "Insert into guessList (guess, bulls, cows) values (?, ?, ?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setString(1, newInstance.getGuessName()); 
			pstmt.setInt(2, newInstance.getBulls());
			pstmt.setInt(3, newInstance.getCows());
			pstmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void markSecretWord() {}

	@Override
	public void unMarkSecretWord() {}

	@Override
	public Guess findSecretWord() {
		return null;
	}

	@Override
	public ArrayList<Guess> getAllInstances() {
		GuessList newGuessList = new GuessList();
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from guessList";
			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				newGuessList.add(new Guess(rs.getInt("guess_id"), rs.getString("guess"), rs.getInt("bulls"), rs.getInt("cows")));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newGuessList.getAllElements();
	}

	@Override
	public void truncateTable() {
		try(Connection connie = ConnectionFactory.getInstance().getConnection()){
			String query = "truncate table guessList";
			Statement stmt = connie.createStatement();
			stmt.executeQuery(query);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
