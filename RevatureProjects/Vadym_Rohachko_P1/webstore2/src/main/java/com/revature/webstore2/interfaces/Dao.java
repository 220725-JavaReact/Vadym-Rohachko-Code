package com.revature.webstore2.interfaces;

import java.util.List;

public interface Dao<T> {
	public void addInstance(T instance);

	public List<T> getAllInstance();

	public void updateInstance(T instance);

	public void deleteInstance(T instance);
}
