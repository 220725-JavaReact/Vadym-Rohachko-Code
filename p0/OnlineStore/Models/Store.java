package Models;

public class Store {

    private int storeId;

    public int getStoreId() {
        return storeId;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    private String storeLocation;

    public Store(int storeId, String storeLocation) {
        this.storeId = storeId;
        this.storeLocation = storeLocation;
    }

    public static void displayStore(Integer storeId){
        System.out.println("Displaying store # " + storeId);
    }
}

