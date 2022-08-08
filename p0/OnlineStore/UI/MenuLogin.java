package UI;

import java.util.Scanner;

import Util.*;

public class MenuLogin {
    Scanner scanner = new Scanner(System.in);
    Login login = new Login();

    public Login getLogin() {

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
            } else if (!MenuHelper.validateEmail(userInput)) {
                Message.wrongEmailFormat();
            } else {
                login.setLogin(userInput);
                //use break and leave exit quale to "false" to pass to the next while loop
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
        //check here against BD? !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (login != null) {
            Logger logger = Logger.getInstance();
            logger.log(Logger.LogLevel.info, "User logged in");
        }

        return login;
    }
}
