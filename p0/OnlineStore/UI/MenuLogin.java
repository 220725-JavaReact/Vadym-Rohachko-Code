package UI;

import java.util.Scanner;

import Models.Login;
import Util.*;

public class MenuLogin {

    public static Login processLoginInUI() {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        boolean exit = false;
        String userInput = "";

        while (!exit) {
            //get user login
            Message.moveToPrevMenu();
            Message.enterLogin();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                login = null;
            } else if (!Helper.validateEmail(userInput)) {
                Message.wrongEmailFormat();
            } else {
                login.setLogin(userInput);
                break;
            }
        }

        while (!exit) {
            //get user pass
            Message.moveToPrevMenu();
            Message.enterPass();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                login = null;
            } else if (userInput.length() < 3) {
                Message.wrongInput();
            } else {
                login.setPass(userInput);
                exit = true;
            }
        }

        if (login != null) {
            Logger logger = Logger.getInstance();
            logger.log(Logger.LogLevel.info, "User logged in");
        }

        return login;
    }
}

