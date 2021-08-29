package erecrutement.finances.gov.ma.MEF.Models;

public class Recover {
    private String login;
    private String password;
    private String confirmpassword;
    private String pin;

    public Recover(String login, String password, String confirmpassword,String pin) {
        this.login = login;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.pin =pin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
