package Models;

import DAL.*;
import Interfaces.IBLLManager;

public class BLLManagerImpl implements IBLLManager {
    User user;

    @Override
    public boolean processLogin(String login, String password) {
        user = new UserDaoImpl().getUserByLoginAndPassword(login, password);
        return user != null ? true : false;
    }

    @Override
    public boolean processRegister(String login, String password, String name, String surname, String cardNumber) {
        return new UserDaoImpl().insertUser(login, password, name, surname, cardNumber);
    }
}

//public class BLLManagerImpl implements IBLLManager {
//    //    public static User processLogin(User user, IDao objDao){
////            return objDao.checkUser(user);
////    }
////
////    public static boolean processRegister(User user, IDao objDao){
////        return objDao.registerUser(user);
////    }
//
//}
