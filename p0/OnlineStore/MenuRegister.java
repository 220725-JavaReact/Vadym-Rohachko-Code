package OnlineStore;

import java.util.Scanner;

public class MenuRegister {
    Scanner scanner = new Scanner(System.in);
    Register register = new Register();

    public Register register() {

        boolean exit = false;
        String userInput = "";

        while (!exit) {
            //set user email
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterLogin();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (!Helper.validateEmail(userInput)) {
                Helper.displayMessWrongEmailFormat();
            } else {
                register.setLogin(userInput);
                //leave here exit = false to pass to the next while loop
                break;
            }
        }

        while (!exit) {
            //get user pass
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterPass();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Helper.displayMessWrongInput();
            } else {
                register.setPass(userInput);
                break;
            }
        }

        while (!exit) {
            //set user surname
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterSurname();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Helper.displayMessWrongInput();
            } else {
                register.setSurname(userInput);
                break;
            }
        }

        while (!exit) {
            //set user name
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterName();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
                } else if (userInput.length() < 3) {
                    Helper.displayMessWrongInput();
            } else {
                register.setName(userInput);
                break;
            }
        }

        while (!exit) {
            //set user address
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterAddress();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Helper.displayMessWrongInput();
            } else {
                register.setAddress(userInput);
                break;
            }
        }

        //check here against BD?
        if (register == null) {
        }

        return register;
    }
}
