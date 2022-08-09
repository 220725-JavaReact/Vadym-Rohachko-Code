package Models;
import DAL.*;


public class BLLManager {
    public static User processLogin(User user, IDao objDao){
            return objDao.checkUser(user);
    }

    public static boolean processRegister(User user, IDao objDao){
        return objDao.registerUser(user);
    }
}
