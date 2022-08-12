package Interfaces;

import Models.Archive;

import java.util.ArrayList;

public interface IArchiveDao {
    ArrayList<Archive> getArchiveByUserId(int userId, SortingType type);

    public enum SortingType{
        order,
        store,
        category,
        time
    }

}


