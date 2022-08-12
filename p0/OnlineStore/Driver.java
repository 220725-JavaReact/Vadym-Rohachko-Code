import BLL.BLLManagerImpl;
import Interfaces.IArchiveDao;
import UI.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Util.*;
import Models.*;

public class Driver {

    public static void main(String[] args) {
        //Menu.manageMenu();
        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        stores.put(11, "Fort Worth");
        stores.put(13, "Dallas");
        stores.put(12, "McKinney");
        stores.put(14, "Allen");
        stores.put(15, "Prosper");


        Logger logger = Logger.getInstance();
        logger.log(Logger.LogLevel.info, "Program started");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] welcomeScreenOptions = {"Login", "Register", "Exit App\n"};
        String[] mainMenuShopOptions = {"List of Stores", "Cart", "Account", "History", "Exit Store\n"};
        String[] menuSortingOptions = {"Order", "Store", "Category", "Time", "Back to prev menu\n"};
        boolean exit = false;

        while (!exit) {
            //Message.welcomeToStore();
            MenuHelper.displayMenu(welcomeScreenOptions, "Welcome to the Store!");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    //Processing login in UI
                    Login login = MenuLogin.processLoginInUI();
                    if (login != null) {
                        //IF LOGIN WAS SUCCESSFUL
                        int userId = new BLLManagerImpl().processLogin(login.getLogin(), login.getPass());
                        if (userId > 0) {
                           System.out.println("Login and Pass are ok! Entering Main Menu...\n");
                            //MAIN MENU
                            MainMenuManager.MainMenuLogic(userId);
                        } else {
                            System.out.println("Wrong login and/or password! Enter again, please...\n");
                        }
                    }

                    break;
                case "2":
                    //Register
                    Register register = new MenuRegister().register();
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

