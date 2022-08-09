package Models;

public class UserModel {

    private int user_id;
    private String lname;
    private String fname;
    private String email;
    private String pass;
    private String card;


    public UserModel(String email, String pass) {
       this.email = email;
       this.pass = pass;
    }

   public UserModel(String email, String pass, String fname, String lname, String card) {
        this.email = email;
        this.pass = pass;
        this.fname = fname;
        this.lname = lname;
        this.card = card;
    }

    public int getId() {
        return this.user_id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String toString() {
        return "[lname = " + lname + " fname = " + fname + " ]";
    }
}
