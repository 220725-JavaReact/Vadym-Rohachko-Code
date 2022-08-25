package com.revature.storage;

import java.util.ArrayList;

import com.revature.models.Guess;

public class GuessList {
private ArrayList<Guess> backingArray = new ArrayList<Guess>();
	
	public GuessList()
	{
		backingArray = new ArrayList<Guess>();
	}
	
	public void add(Guess newGuess)
	{
		backingArray.add(newGuess);
	}
	
	public ArrayList<Guess> getAllElements()
	{
		return backingArray;
	}

}
