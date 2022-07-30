//package OnlineStore;
//
//import java.util.HashMap;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.ArrayList;
//
//public class Helper {
//    public static void displayMenu(String[] menu) {
//        for (int i = 0; i < menu.length ; i++){
//            if(i == menu.length - 1){
//                System.out.printf("[%d] - %s",0 , menu[i]);
//            } else {
//                System.out.printf("[%d] - %s\n",i + 1 , menu[i]);
//            }
//        }
//        System.out.printf("Choose your option: ");
//    }
//
//    public static void displayMenu(HashMap menu) {
//        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(menu.keySet());
//        for (int i = 0; i < listOfKeys.size() ; i++){
////            if(i == listOfKeys.size() - 1){
////                System.out.printf("[%d] - %s\n",0 , "Back to prev menu");
////            } else {
//                System.out.printf("[%d] - %s\n",listOfKeys.get(i), menu.get(listOfKeys.get(i)));
////            }
//        }
//        System.out.printf("[%d] - %s\n",0 , "Back to prev menu");
//        System.out.printf("Choose your option: ");
//    }
//
//    public static boolean validateEmail(String email) {
////        Pattern pattern =
////                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
////        Matcher matcher = pattern.matcher(email);
////        boolean result = matcher.find();
////        return result;
//
//        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
//        return Pattern.compile(regex).matcher(email).matches();
//    }
//
//    public static void displayMessChooseOption() {
//        System.out.printf("Choose your option: ");
//    }
//    public static void displayMessListOfStores() {
//        System.out.println("List of Stores:");
//    }
//    public static void displayMessChooseStore() {
//        System.out.println("Choose Store:");
//    }
//    public static void displayMessMainMenu() {
//        System.out.println("Main Menu:");
//    }
//
//    public static void displayMessLogin() {
//        System.out.println("Registration successful! Please login.\n");
//    }
//
//    public static void displayMessWrongEmailFormat() {
//        System.out.println("Wrong Email format!\n");
//    }
//
//    public static void displayMessEnterAddress() {
//        System.out.printf("Enter your address: ");
//    }
//
//    public static void displayMessEnterSurname() {
//        System.out.printf("Enter your surname: ");
//    }
//
//    public static void displayMessEnterName() {
//        System.out.printf("Enter your name: ");
//    }
//
//    public static void displayMessEnterBuilding() {
//        System.out.printf("Enter building: ");
//    }
//
//    public static void displayMessEnterStreet() {
//        System.out.printf("Enter street: ");
//    }
//
//    public static void displayMessEnterApartment() {
//        System.out.printf("Enter apartment: ");
//    }
//
//    public static void displayMessEnterState() {
//        System.out.printf("Enter surname: ");
//    }
//
//    public static void displayMessEnterZip() {
//        System.out.printf("Enter zip: ");
//    }
//
//    public static void displayMessMoveToPrevMenu() {
//        System.out.println("[q] to move to previous menu");
//    }
//
//    public static void displayMessEnterLogin() {
//        System.out.printf("Enter Login: ");
//    }
//
//    public static void displayMessEnterPass() {
//        System.out.printf("Enter Pass: ");
//    }
//
//    public static void displayMessGoodBy() {
//        System.out.println("We will be happy to see you again!");
//    }
//
//    public static void displayMessWrongInput() {
//        System.out.println("Wrong input!\n");
//    }
//
//    public static void displayMessEmptyInput() {
//        System.out.println("Empty input!\n");
//    }
//
//    public static void displayMessWelcomeToStore() {
//        System.out.println("Welcome to our store!\n");
//    }
//}
