package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.ConnectionFactory;
import Models.BLLManager;

public class DaoPostgresql implements IDao<BLLManager> {

//    @Override
//    public void addInstance(BLLManager newInstance) {
//        // TODO Auto-generated method stub
//        try {
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//                String query = "insert into users(fname, lname,email,pass,card_number) " +
//						"values('John','Rambow','john.rambo@gmail.com', '111', '1111222233334441');";
//                PreparedStatement pstmt = conn.prepareStatement(query);
//                pstmt.execute();
//                System.out.println("Connection successful!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            System.out.println("Error connection!");
//        }
//    }

    @Override
    public void addInstance(BLLManager newInstance) {
        // TODO Auto-generated method stub
        try {
            int id = 1;
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                String query = "select * from users";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) {
                    //teams.add(new Team(rs.getString("team_name"), rs.getInt("points"), rs.getInt("team_id")));
                    System.out.println(rs.getString("lname"));
                }
                System.out.println("Connection successful!");
            } catch (SQLException e) {
                System.out.println("Error !");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error connection!");
        }
    }


//    public Team getByName(String name) {
//        // TODO Auto-generated method stub
//        try(Connection connie = ConnectionFactory.getInstance().getConnection()){
//            String query = "select * from teams where team_name = ?";
//            PreparedStatement pstmt = connie.prepareStatement(query);
//            pstmt.setString(1, name);
//            ResultSet rs = pstmt.executeQuery();
//            if(rs.next()) {
//                return new Team(rs.getString("team_name"), rs.getInt("points"), rs.getInt("team_id"));
//            }
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public BLLManager getByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BLLManager[] getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateInstance(BLLManager updateInstance) {
        // TODO Auto-generated method stub

    }

}
