package Interfaces;

import Models.Archive;

import java.util.ArrayList;

public interface IArchiveDao {
    ArrayList<Archive> getArchiveByUserId(int userId);
}


