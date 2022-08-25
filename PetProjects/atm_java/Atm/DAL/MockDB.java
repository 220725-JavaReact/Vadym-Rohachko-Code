package Atm.DAL;

import Atm.Models.Card;

import java.util.ArrayList;

public class MockDB {

    private static ArrayList<Card> setData(){
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card("1111222233334444", 1111, "John", "Brown", 150.22));
        cards.add(new Card("2222111133334444", 2222, "Amy", "Whitehouse", 180.02));
        cards.add(new Card("3333111122223333", 3333, "Bob", "Raven", 350.21));
        cards.add(new Card("4444111133332222", 4444, "Frida", "Callo", 250.34));
        cards.add(new Card("3333222211114444", 5555, "Dawn", "Smith", 1150.54));
        cards.add(new Card("4444222233331111", 6666, "Ashly", "Dickens", 50.43));
        return cards;
    }

    public static ArrayList<Card> getData(){
        return setData();
    }

    public static int getQuantity(){
            return getData().size();
    }

    public static boolean checkCard(String number){
        for(Card card: getData()){
            if(card.getCardNum().equals(number)){
                return true;
            }
        }
        return false;
    }

    public static Card getCard(String number){
        for(Card card: getData()){
            if(card.getCardNum().equals(number)){
                return card;
            }
        }
        return null;
    }


}
