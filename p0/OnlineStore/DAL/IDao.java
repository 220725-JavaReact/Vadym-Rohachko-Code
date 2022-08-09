package DAL;

import Models.User;

public interface IDao<T> {
    void addUser(T newInstance);
    User checkUser(T newInstance);
    boolean registerUser(T newInstance);
//	void addInstance(T newInstance);
//	T getByName(String name);
//	T[] getAll();
//	void updateInstance(T updateInstance);
}
