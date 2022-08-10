package Interfaces;

public interface IBLLManager {
    //    static User processLogin(User user, IDao objDao) {
//        return null;
//    }
//
//    static boolean processRegister(User user, IDao objDao) {
//        return false;
//    }
    boolean processLogin(String login, String password);

    boolean processRegister(String login, String password, String name, String surname, String cardNumber);
}
