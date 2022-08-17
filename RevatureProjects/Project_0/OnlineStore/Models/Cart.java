package Models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Cart {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public int getCartId() {
        return cartId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerItem() {
        return pricePerItem;
    }

    public Timestamp getRecordCreatedAt() {
        return recordCreatedAt;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public boolean isAvailableProduct() {
        return availableProduct;
    }

    private int cartId;
    private int orderId;
    private int itemId;
    private int storeId;
    private int productId;
    private int categoryId;
    private String fname;
    private String lname;
    private String productName;
    private String description;
    private String category;
    private String storeLocation;
    private int quantity;
    private BigDecimal pricePerItem;
    private Timestamp recordCreatedAt;
    private int availableQuantity;
    private boolean availableProduct;

    public Cart(int userId, int storeId, int productId, int quantity) {
        this.userId = userId;
        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(int userId, int storeId, int productId, int categoryId,
                String productName, String description, String category,
                String storeLocation, int quantity, BigDecimal pricePerItem) {
        this.userId = userId;
        this.storeId = storeId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.storeLocation = storeLocation;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }
    public Cart(int userId,
                int cartId,
                int orderId,
                int itemId,
                int storeId,
                int productId,
                int categoryId,
                String fname,
                String lname,
                String productName,
                String description,
                String category,
                String storeLocation,
                int quantity,
                BigDecimal pricePerItem,
                Timestamp recordCreatedAt,
                int availableQuantity,
                boolean availableProduct) {
        this.userId = userId;
        this.cartId = cartId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.storeId = storeId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.fname = fname;
        this.lname = lname;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.storeLocation = storeLocation;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.recordCreatedAt = recordCreatedAt;
        this.availableQuantity = availableQuantity;
        this.availableProduct = availableProduct;
    }


}
