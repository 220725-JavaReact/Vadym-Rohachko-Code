package DAL;

import Interfaces.IUserDao;
import Models.User;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements IUserDao {
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from users where email = ? and pass = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("fname"), rs.getString("lname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUser(String login, String password, String fName, String lName, String cardNumber) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "insert into users(fname, lname,email,pass,card_number) values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, login);
            pstmt.setString(4, password);
            pstmt.setString(5, cardNumber);
            pstmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
