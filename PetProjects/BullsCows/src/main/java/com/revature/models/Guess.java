package com.revature.models;

public class Guess {
	private int guessID;
	private String guessName;
	private int bulls;
	private int cows;

	public Guess(String guessName, int bulls, int cows) {
		super();
		// this.guessID = guessID;
		this.guessName = guessName;
		this.bulls = bulls;
		this.cows = cows;
	}

	public Guess(int guessID, String guessName, int bulls, int cows) {
		super();
		this.guessID = guessID;
		this.guessName = guessName;
		this.bulls = bulls;
		this.cows = cows;
	}

	public int getGuessID() {
		return guessID;
	}

	public void setGuessID(int guessID) {
		this.guessID = guessID;
	}

	public String getGuessName() {
		return guessName;
	}

	public void setGuessName(String guessName) {
		this.guessName = guessName;
	}

	public int getBulls() {
		return bulls;
	}

	public void setBulls(int bulls) {
		this.bulls = bulls;
	}

	public int getCows() {
		return cows;
	}

	public void setCows(int cows) {
		this.cows = cows;
	}
}
