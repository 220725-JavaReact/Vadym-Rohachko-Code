package UI;

import java.util.Scanner;

public class MenuRegister {

    public static Register register() {

        Scanner scanner = new Scanner(System.in);
        Register register = new Register();

        boolean exit = false;
        String userInput = "";

        while (!exit) {
            //set user email
            Message.moveToPrevMenu();
            Message.enterLogin();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (!MenuHelper.validateEmail(userInput)) {
                Message.wrongEmailFormat();
            } else {
                register.setLogin(userInput);
                //leave here exit = false to pass to the next while loop
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
                register = null;
            } else if (userInput.length() < 3) {
                Message.wrongInput();
            } else {
                register.setPass(userInput);
                break;
            }
        }

        while (!exit) {
            //set user surname
            Message.moveToPrevMenu();
            Message.enterSurname();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Message.wrongInput();
            } else {
                register.setSurname(userInput);
                break;
            }
        }

        while (!exit) {
            //set user name
            Message.moveToPrevMenu();
            Message.enterName();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Message.wrongInput();
            } else {
                register.setName(userInput);
                break;
            }
        }

        while (!exit) {
            //set user address
            Message.moveToPrevMenu();
            Message.enterAddress();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Message.emptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                register = null;
            } else if (userInput.length() < 3) {
                Message.wrongInput();
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
