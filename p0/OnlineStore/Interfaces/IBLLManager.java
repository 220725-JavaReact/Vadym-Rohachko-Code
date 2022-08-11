package Interfaces;

import Models.*;

import java.util.ArrayList;

public interface IBLLManager {
    boolean processLogin(String login, String password);

    boolean processRegister(String login, String password, String name, String surname, String cardNumber);

    Product processProductById(int id);

    ArrayList<Product> processProductsByCategory(int id);
    ArrayList<Product> processProductsByStore(int id);

    ArrayList<Archive> processArchivesByUserId(int userId);
}

