package Interfaces;

import java.util.ArrayList;
import Models.*;

public interface IStoreDao {
    ArrayList<Store> getStores();
    Store getStore(int storeId);
}
