package DAL;

import Interfaces.ICartDao;
import Models.Cart;
import Models.Product;
import Util.ConnectionFactory;
import DAL.*;
import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDaoImpl implements ICartDao {
    private String query1 = "select * from inventories ";
    private String query2 = "inner join products ";
    private String query3 = "on inventories.product_id = products.product_id ";
    private String query4 = "inner join categories ";
    private String query5 = "on products.category_id = categories.category_id ";
    private String query6 = "inner join stores ";
    private String query7 = "on stores.store_id = inventories.store_id ";
    private String query = query1 + query2 + query3 + query4 + query5 + query6 + query7;

    @Override
    public ArrayList<Cart> getRecordsFromCart(int userId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from carts where user_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Cart> carts = new ArrayList<>();
            while(rs.next()){
                Cart cart =   extractCartFromResultSet(rs);
                carts.add(cart);
            }
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart getSingleRecordFromCart(Cart cart) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from carts where user_id = ? and store_id = ? and product_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cart.getUserId());
            pstmt.setInt(2, cart.getStoreId());
            pstmt.setInt(3, cart.getProductId());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Cart cartFull =  extractCartFromResultSet(rs);
                return cartFull;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProductToCart(Cart cart) {
        User user = new UserDaoImpl().getUserById(cart.getUserId());
        Store store = new StoreDaoImpl().getStore(cart.getStoreId());
        Product product = new ProductDaoImpl().getAvailableProductFromInventoryById(cart.getProductId());
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "insert into carts (user_id, store_id, product_id, quantity, " +
                    "product_name, description, category, fname, lname, store_location, category_id, price_per_item) values(?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cart.getUserId());
            pstmt.setInt(2, cart.getStoreId());
            pstmt.setInt(3, cart.getProductId());
            pstmt.setInt(4, cart.getQuantity());
            pstmt.setString(5, product.getProductName());
            pstmt.setString(6, product.getDescription());
            pstmt.setString(7, product.getCategory());
            pstmt.setString(8, user.getFname());
            pstmt.setString(9, user.getLname());
            pstmt.setString(10, store.getStoreLocation());
            pstmt.setInt(11, product.getCategoryId());
            pstmt.setDouble(12, product.getPrice());
            int count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProductInCart(Cart cart) {
        try {
            Cart cartTemp = getSingleRecordFromCart(cart);
            int affectedrows;
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "update carts set quantity = ? where user_id = ? and store_id = ? and product_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cart.getQuantity() + cartTemp.getQuantity());
            pstmt.setInt(2, cart.getUserId());
            pstmt.setInt(3, cart.getStoreId());
            pstmt.setInt(4, cart.getProductId());
            affectedrows = pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Updated " + affectedrows + " rows!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Cart extractCartFromResultSet(ResultSet rs) throws SQLException {
        Cart cart = new Cart(
                rs.getInt("user_id"),
                rs.getInt("cart_id"),
                rs.getInt("order_id"),
                rs.getInt("item_id"),
                rs.getInt("store_id"),
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("product_name"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getString("store_location"),
                rs.getInt("quantity"),
                rs.getBigDecimal("price_per_item"),
                rs.getTimestamp("record_created_at"),
                rs.getInt("available_quantity"),
                rs.getBoolean("available_product"));
        return cart;
    }
}
