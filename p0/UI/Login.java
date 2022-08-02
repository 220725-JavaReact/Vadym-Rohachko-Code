package UI;

import java.util.HashMap;



public class Login {
    private String _login = "";
    private String _pass = "";
    private boolean _isRejected = false;

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        this._login = login;
    }

    public String getPass() {
        return _pass;
    }

    public void setPass(String pass) {
        this._pass = pass;
    }

    public boolean isRejected() {
        return _isRejected;
    }


}
