package DAL;

import Interfaces.IStoreDao;
import Models.Store;
import Models.User;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StoreDaoImpl implements IStoreDao {
    @Override
    public ArrayList<Store> getStores() {
        return null;
    }

    @Override
    public Store getStore(int storeId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from stores where store_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return  new Store(rs.getInt("store_id"),rs.getString("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
