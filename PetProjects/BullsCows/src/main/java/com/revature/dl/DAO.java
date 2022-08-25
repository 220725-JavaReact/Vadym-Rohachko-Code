package com.revature.dl;

import java.util.ArrayList;

public interface DAO<T> {
	void addInstance(T newInstance);
	void markSecretWord();
	void unMarkSecretWord();
	void truncateTable();
	T findSecretWord();
	ArrayList<T> getAllInstances();	
}
