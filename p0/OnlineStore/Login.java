package OnlineStore;

import java.util.HashMap;



public class Login {
    private String _login = "";
    private String _pass = "";
    private boolean _isRejected = false;

    public String get_login() {
        return _login;
    }

    public void set_login(String login) {
        this._login = login;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String pass) {
        this._pass = pass;
    }

    public boolean is_isRejected() {
        return _isRejected;
    }

    public void set_isRejected(boolean isRejected) {
        this._isRejected = isRejected;
    }

}
