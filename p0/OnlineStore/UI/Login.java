package UI;

import java.util.HashMap;



public class Login {
    private String login = "";
    private String pass = "";
    private boolean isRejected = false;

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRejected() {
        return isRejected;
    }


}
