package DAL;

import Interfaces.IProductDao;
import Models.Product;
import Util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ProductDaoImpl implements IProductDao {
    private String query1 = "select * from inventories ";
    private String query2 = "inner join products ";
    private String query3 = "on inventories.product_id = products.product_id ";
    private String query4 = "inner join categories ";
    private String query5 = "on products.category_id = categories.category_id ";
    private String query6 = "inner join stores ";
    private String query7 = "on stores.store_id = inventories.store_id ";

    private String query = query1 + query2 + query3 + query4 + query5 + query6 + query7;

    @Override
    public Product getAvailableProductFromInventoryById(int userId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query8 = "where products.product_id = ?;";
            query += query8;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAvailableProductsFromInventoryByCategory(int categoryId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query8 = "where categories.category_id = ?;";
            query += query8;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryId);
            ArrayList<Product> products = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAvailableProductsFromInventoryByStore(int storeId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query8 = "where stores.store_id = ?;";
            query += query8;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, storeId);
            ArrayList<Product> products = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAllAvailableProductsFromInventory() {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query8 = "order by stores.store_id, products.product_id;";
            query += query8;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getInt("product_id"), rs.getInt("category_id"),
                rs.getString("product_name"), rs.getString("description"),
                rs.getDouble("price"), rs.getString("category"),
                rs.getInt("quantity"), rs.getInt("store_id"), rs.getString("city"));
        return product;
    }
}
