import BLL.BLLManagerImpl;

import java.util.Arrays;
import java.util.Scanner;

import DAL.StoreDaoImpl;
import UI.*;
import Util.*;
import Models.*;
import  Enum.*;

public class Driver {
    public static void main(String[] args) {

        Logger logger = Logger.getInstance();
        logger.log(Logger.LogLevel.info, "Program started");
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] welcomeScreenOptions = {"Login", "Register", "Exit App\n"};
        boolean exit = false;

        while (!exit) {
            //Message.welcomeToStore();
            MenuHelper.displayMenu(welcomeScreenOptions, "Welcome to the Store!", "");
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    //LOGIN
                    Login login = MenuLogin.processLoginInUI();
                    if (login != null) {
                        int userId = new BLLManagerImpl().processLogin(login.getLogin(), login.getPass());
                        if (userId > 0) {
                            System.out.println("Login and Pass are ok! Entering Main Menu...\n");
                            //MAIN MENU
                            MainMenuManager.processMainMenu(userId);
                        } else {
                            System.out.println("Wrong login and/or password! Enter again, please...\n");
                        }
                    }
                    break;
                case "2":
                    //REGISTER
                    Register register = MenuRegister.register();
                    if (register != null) {
                        boolean isRegistered =
                                new BLLManagerImpl().processRegister(register.getLogin(),
                                        register.getPass(), register.getName(),
                                        register.getSurname(), register.getCardNumber());
                        if (isRegistered) {
                            Message.registerSuccess();
                        } else {
                            Message.registerFailed();
                        }
                    }
                    break;
                case "q":
                    Message.goodBy();
                    logger.log(Logger.LogLevel.info, "Program stopped");
                    System.exit(0);
                default:
                    Message.wrongInput();
            }
        }// end login register menu
    }
}

