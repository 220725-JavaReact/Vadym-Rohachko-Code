package OnlineStore;

import java.util.Scanner;

public class MenuLogin {
    Scanner scanner = new Scanner(System.in);
    Login login = new Login();

    public Login getLogin() {

        boolean exit = false;
        String userInput = "";

        while (!exit) {
            //get user login
            Helper.displayMessMoveToPrevMenu();
            Helper.displayMessEnterLogin();
            userInput = scanner.nextLine();
            if (userInput.length() == 0) {
                Helper.displayMessEmptyInput();
            } else if (userInput.equals("q")) {
                exit = true;
                login = null;
            } else if (!Helper.validateEmail(userInput)) {
                Helper.displayMessWrongEmailFormat();
            } else {
                login.setLogin(userInput);
                //use break and leave exit quale to "false" to pass to the next while loop
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
                login = null;
            } else if (userInput.length() < 3) {
                Helper.displayMessWrongInput();
            } else {
                login.setPass(userInput);
                exit = true;
            }
        }
        //check here against BD?
        if (login == null) {}

        return login;
    }
}
