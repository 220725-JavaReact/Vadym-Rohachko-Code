package DAL;

public interface DAO<T> {
	void addInstance(T newInstance);
	T getByName(String name);
	T[] getAll();
	void updateInstance(T updateInstance);
}
