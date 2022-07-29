package OnlineStore;

public class Register {
    private String _login = "";
    private String _pass = "";
    private String _surname = "";
    private String _name = "";
    private String _address;
    private String _addressBuilding = "";
    private String _addressStreet = "";
    private String _addressApartment = "";
    private String _addressCity = "";
    private String _addressState = "";
    private boolean _isRejected = false;


    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        this._surname = address;
    }

    public String getSurname() {
        return _surname;
    }

    public void setSurname(String surname) {
        this._surname = surname;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getAddressBuilding() {
        return _addressBuilding;
    }

    public void setAddressBuilding(String building) {
        this._addressBuilding = building;
    }

    public String getAddressStreet() {
        return _addressStreet;
    }

    public void setAddressStreet(String street) {
        this._addressStreet = street;
    }

    public String getAddressApartment() {
        return _addressApartment;
    }

    public void setAddressApartment(String apartment) {
        this._addressApartment = apartment;
    }

    public String getAddressCity() {
        return _addressCity;
    }

    public void setAddressCity(String city) {
        this._addressCity = city;
    }

    public String getAddressState() {
        return _addressState;
    }

    public void setAddressState(String state) {
        this._addressState = state;
    }

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
