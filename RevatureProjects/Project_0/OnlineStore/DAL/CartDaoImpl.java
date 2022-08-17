package DAL;

import Interfaces.ICartDao;
import Models.Cart;
import Models.Product;
import Util.ConnectionFactory;
import Models.*;
import Enum.*;

import java.sql.*;
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

    //    @Modifying
//    @Transaction
    @Override
    public ArrayList<Cart> processPayment(ArrayList<Cart> carts) {
        Cart cart = carts.stream().findFirst().orElse(null);
        int userId = cart.getUserId();

        try {
            String query = "Select processPayment(?)";
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            //org.postgresql.util.PSQLException: A result was returned when none was expected.
            pstmt.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
            ArrayList<Cart> cartsNew = new CartDaoImpl().getRecordsFromCart(userId);
            if (cartsNew.size() > 0) {
                System.out.println("Payment failed!");
                return cartsNew;
            } else {
                System.out.println("Payment confirmed!");
                return null;
            }
        }
        return null;
    }

    @Override
    public CommandWord increaseQuantityInRecordByOne(int userId, int cartId) {
        try {
            int quantity = new CartDaoImpl().getSingleRecordFromCart(cartId).getQuantity();
            quantity += 1;
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "update carts set quantity = ? where cart_id = ? and user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartId);
            pstmt.setInt(3, userId);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return CommandWord.UPDATE;
            } else {
                return CommandWord.FAILURE;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return CommandWord.FAILURE;
        }
    }

    @Override
    public CommandWord decreaseQuantityInRecordByOne(int userId, int cartId) {
        try {
            int quantity = new CartDaoImpl().getSingleRecordFromCart(cartId).getQuantity();
            if (quantity > 0) {
                quantity -= 1;
                if (quantity == 0) {
                    new CartDaoImpl().deleteProductFromCart(userId, cartId);
                    return CommandWord.UPDATE;
                }
            } else {
                return CommandWord.FAILURE;
            }
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "update carts set quantity = ? where cart_id = ? and user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartId);
            pstmt.setInt(3, userId);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return CommandWord.UPDATE;
            } else {
                return CommandWord.FAILURE;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return CommandWord.FAILURE;
        }
    }

    @Override
    public CommandWord increaseQuantityInRecordByMany(int userId, int cartId, int qty) {
        try {
            int quantity = new CartDaoImpl().getSingleRecordFromCart(cartId).getQuantity();
            quantity += qty;
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "update carts set quantity = ? where cart_id = ? and user_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartId);
            pstmt.setInt(3, userId);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return CommandWord.UPDATE;
            } else {
                return CommandWord.FAILURE;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return CommandWord.FAILURE;
        }
    }

    @Override
    public CommandWord decreaseQuantityInRecordByMany(int userId, int cartId, int qty) {
        try {
            Cart locatCart = new CartDaoImpl().getSingleRecordFromCart(cartId);
            int quantity = locatCart.getQuantity();
            if (quantity - qty > 0) {
                quantity -= qty;
            } else if (quantity - qty == 0) {
                new CartDaoImpl().deleteProductFromCart(userId, cartId);
                return CommandWord.UPDATE;
            } else {
                return CommandWord.FAILURE_DUE_TO_HIGH_AMOUNT;
            }
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "update carts set quantity = ? where cart_id = ? and user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartId);
            pstmt.setInt(3, userId);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return CommandWord.UPDATE;
            } else {
                return CommandWord.FAILURE;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return CommandWord.FAILURE;
        }
    }


    @Override
    public void deleteProductFromCart(int userId, int cartId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "delete from carts where user_id = ? and cart_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, cartId);
            int counter = pstmt.executeUpdate();
            if (counter == 1) {
                //System.out.println("Deleted one record.");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(int userId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "delete from carts where user_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            int counter = pstmt.executeUpdate();
            if (counter == 1) {
                //System.out.println("Deleted one record.");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Cart> getRecordsFromCart(int userId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from carts where user_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                Cart cart = extractCartFromResultSet(rs);
                carts.add(cart);
            }
            return carts;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart getSingleRecordFromCart(int cartId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from carts where cart_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cartId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Cart cartFull = extractCartFromResultSet(rs);
                return cartFull;
            }
        } catch (Exception e) {
            //e.printStackTrace();
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
            if (rs.next()) {
                Cart cartFull = extractCartFromResultSet(rs);
                return cartFull;
            }
        } catch (Exception e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
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
            //e.printStackTrace();
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
