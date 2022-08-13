package DAL;

import Interfaces.IStoreDao;
import Models.Store;
import Models.User;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StoreDaoImpl implements IStoreDao {
    public ArrayList<Store> getStores() {
        try {
            String query = "select distinct city, stores.store_id " +
                    "from stores  " +
                    "inner join inventories " +
                    "on stores.store_id = inventories.store_id;";
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Store> stores = new ArrayList<>();
            while (rs.next()) {
                stores.add(new Store(rs.getInt("store_id"), rs.getString("city")));
            }
            return stores;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getStore(int storeId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from stores " +
                    "inner join inventories " +
                    "on stores.store_id = inventories.store_id " +
                    "where stores.store_id = ? limit 1";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Store(rs.getInt("store_id"), rs.getString("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
