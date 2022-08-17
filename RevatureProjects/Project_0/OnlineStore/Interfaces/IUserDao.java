package Interfaces;
import Models.*;

public interface IUserDao {
    User getUserById(int id);
    User getUserByLoginAndPassword(String login, String password);
    boolean insertUser(String login, String password, String fName, String lName, String cardNumber);
    //boolean updateUser(User user);
    //boolean deleteUser(int id);
}

