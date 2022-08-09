package DAL;

public interface IDao<T> {
	void addInstance(T newInstance);
	T getByName(String name);
	T[] getAll();
	void updateInstance(T updateInstance);
}
