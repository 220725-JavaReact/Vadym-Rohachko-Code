package com.revature.webstore.interfaces;

import java.util.List;

public interface IDao<T> {
	  public void addInstance(T instance);
	    public List<T> getAllInstance();
	    public void updateInstance(T instance);
	    public void deleteInstance(T instance);
}
