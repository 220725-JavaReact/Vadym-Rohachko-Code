package Util;

public class Regex {
    public static boolean isReadyToIncreaseByOne(String s) {
        return s.matches("^([1-9][0-9]*[+]{1})$");
    }

    public static boolean isReadyToDecreaseByOne(String s) {
        return s.matches("^([1-9][0-9]*[-]{1})$");
    }

    public static boolean isReadyToRemoveFromCart(String s) {
        return s.matches("^([1-9][0-9]*[!]{1})$");
    }

    public static boolean isReadyToAddMultiple(String s) {
        return s.matches("^([1-9][0-9]*[+]{1}[1-9][0-9]*)$");
    }

    public static boolean isReadyToSubstractMultiple(String s) {
        return s.matches("^([1-9][0-9]*[-]{1}[1-9][0-9]*)$");
    }

    public static boolean isReadyToRemoveAllFromCart(String s) {
        return s.matches("^([*]{1})$");
    }
}
