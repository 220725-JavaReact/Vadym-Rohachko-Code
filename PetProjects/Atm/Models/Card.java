package Atm.Main.Models;

import java.util.Scanner;

public class Card {
    private int pin;
    private String firstName;
    private String lastName;
    private double balance;

    private String cardNum;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
    	//System.out.println("Balanc()");
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    Scanner scanner = new Scanner(System.in);

    public Card(String cardNum, int pin, String firstName, String lastName, double balance) {
        this.cardNum = cardNum;
        this.pin = pin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public void deposit() {
        try {
            System.out.println("How much $$ would you like to deposit?");
            double deposit = Double.parseDouble(scanner.nextLine());
            this.balance += deposit;
            System.out.println("Thank you for your $$. Your new balance is: " + this.getBalance());
        } catch (Exception e) {
            System.out.println("Wrong input! Try again...");
        }
    }

    public void withdraw() {
        try {
            System.out.println("How much $$ would you like to withdraw: ");
            double withdrawal = Double.parseDouble(scanner.nextLine());
            //check if the user has enough money
            if (this.getBalance() < withdrawal) {
                System.out.println("Insufficient balance :(");
            } else {
                this.setBalance(this.getBalance() - withdrawal);
                System.out.println("$$ was withdrawn. Your new balance is: " + this.getBalance());
            }
        } catch (Exception e) {
            System.out.println("Wrong input! Try again...");
        }

    }

    void balance() {    	
        this.getBalance();
    }
}
