package com.revature.dl;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.word;
import com.revature.util.ConnectionFactory;

public class wordDAO implements DAO<word> {

	@Override
	public void addInstance(word newInstance) {
		try (Connection connie = ConnectionFactory.getInstance().getConnection()) {
			String query = "Insert into wordsList (wordLength, wordName) values (?, ?)";
			PreparedStatement pstmt = connie.prepareStatement(query);
			pstmt.setInt(1, newInstance.getLength());
			pstmt.setString(2, newInstance.getWordName());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void markSecretWord() {// change "chosen" value to true
		try (Connection connie = ConnectionFactory.getInstance().getConnection()) {
			String query = "update wordsList set chosen = true where wordName IN (SELECT wordName FROM wordsList "
					+ "ORDER BY random() " + "LIMIT 1)";

			Statement stmt = connie.createStatement();
			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unMarkSecretWord() {
		try (Connection connie = ConnectionFactory.getInstance().getConnection()) {
			String query = "update wordsList set chosen = false where chosen = true";

			Statement stmt = connie.createStatement();
			stmt.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public word findSecretWord() {// return the "chosen" word
		try (Connection connie = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM wordsList where chosen = true";

			Statement stmt = connie.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				String wordName = rs.getString("wordName");
				word newWord = new word(wordName.length(), wordName);
				return newWord;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<word> getAllInstances() {
		return null;
	}

	@Override
	public void truncateTable() {
	}
}























