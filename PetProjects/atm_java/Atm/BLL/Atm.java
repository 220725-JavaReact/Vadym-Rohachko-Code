package Atm.BLL;

import Atm.DAL.MockDB;
import Atm.Menu.Menu;
import Atm.Models.Card;
import Atm.Util.Logger;

import java.util.Scanner;
//import java.util.logging.Logger;

public class Atm {
    public void manager() {
        String custChoice = "";
        Scanner scanner = new Scanner(System.in);
         Logger logger = Logger.getInstance();
        logger.log(Logger.LogLevel.info, "Program started");

        while (true) {
            Menu.printWelcomeMenu();
            custChoice = scanner.nextLine();
            if (!custChoice.equals("1") && !custChoice.equals("2")) {
                System.out.println("Wrong input! Try again...\n");
            } else if (custChoice.equals("2")) {
                System.out.println("Good buy!\n");
                System.exit(0);
            } else {
                System.out.print("Enter your card number, please:");
                String number = scanner.nextLine();
                Card card = MockDB.getCard(number);
                if (card == null) {
                    System.out.println("Wrong card number! Try again...\n");
                } else {
                    System.out.println("\nWelcome to your account!");
                    while (true) {
                        Menu.printMainMenu();
                        custChoice = scanner.nextLine();
                        if (Integer.parseInt(custChoice) < 1 || Integer.parseInt(custChoice) > 4) {
                            System.out.println("Wrong input! Try again...\n");
                        } else if (custChoice.equals("4")) {
                            System.out.println();
                            break;
                        } else if (custChoice.equals("1")) {
                            System.out.println("How much $$ would you like to deposit?");
                            double userInput = Double.parseDouble(scanner.nextLine());
                            card.deposit(userInput);
                            System.out.println("Thank you for your $$.");
                        } else if (custChoice.equals("2")) {
                            System.out.println("How much $$ would you like to withdraw: ");
                           double userInput =  Double.parseDouble(scanner.nextLine());
                           boolean result = card.withdraw(userInput);
                            if (result) {
                                System.out.println("Withdrawn successfully!");
                            } else {
                                System.out.println("Insufficient balance :(");
                            }
                        } else if (custChoice.equals("3")) {
                            System.out.println("Your current balance is " + card.getBalance() + "\n");
                        }
                    }
                }
            }
        }
    }

    public void checkCard(String cardNumber) {
        Scanner scanner = new Scanner(System.in);
        Card card;
        String number;

        while (true) {
            try {
                number = scanner.nextLine();
                card = MockDB.getCard(cardNumber);
                if (card != null) {
                    break;
                } else {
                    System.out.println("Card not recognized. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Card not recognized. Please try again.");
            }
        }
    }
}
