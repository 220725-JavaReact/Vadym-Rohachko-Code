package Atm.Models;

import java.util.Scanner;

public class Card {
    public Card() {
    }

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

    public void deposit(double deposit) {
        try {
            this.balance += deposit;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean withdraw(double withdrawal) {
        try {
            //check if the user has enough money
            if (this.getBalance() < withdrawal) {
                return false;
            } else {
                this.setBalance(this.getBalance() - withdrawal);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    void balance() {
        this.getBalance();
    }



}
