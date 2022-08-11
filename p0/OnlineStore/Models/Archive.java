package Models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Archive {
    private int userId;
    private int orderId;
    private int itemId;
    private int storeId;
    private int productId;
    private int categoryId;
    private String lname;
    private String fname;
    private String productName;
    private String description;
    private String category;
    private String storeLocation;
    private int quantity;
    private BigDecimal pricePerItem;
    private BigDecimal priceTotal;
    private Timestamp orderCreatedAt;
    private Timestamp archiveCreatedAt;

    public Archive(int userId, int orderId, int itemId, int storeId, int productId,
                   int categoryId, String lname, String fname, String productName,
                   String description, String category, String storeLocation,
                   int quantity, BigDecimal pricePerItem, Timestamp orderCreatedAt,
                   Timestamp archiveCreatedAt) {
        this.userId = userId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.storeId = storeId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.lname = lname;
        this.fname = fname;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.storeLocation = storeLocation;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.orderCreatedAt = orderCreatedAt;
        this.archiveCreatedAt = archiveCreatedAt;
        this.priceTotal = pricePerItem.multiply(BigDecimal.valueOf(this.quantity));
    }

}
