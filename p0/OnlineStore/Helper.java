package OnlineStore;

public class Helper {
    public static void displayMenu(String[] menu){
        for (String item : menu) {
            System.out.println(item);
        }
        System.out.printf("Choose your option: ");
    }

    public static void displayMessMoveToPrevMenu(){
        System.out.println("[q] to move to previous menu");
    }
    public static void displayMessEnterLogin(){
        System.out.printf("Enter Login: ");
    }

    public static void displayMessEnterPass(){
        System.out.printf("Enter Pass: ");
    }

    public static void displayMessGoodBy(){
        System.out.println("We will be happy to see you again!");
    }

    public static void displayMessWrongInput() {
        System.out.println("Wrong input!");
    }
    public static void displayMessEmptyInput() {
        System.out.println("Empty input!");
    }

    public static void displayMessWelcomeToStore(){
        System.out.println("Welcome to our store!\n");
    }
}
