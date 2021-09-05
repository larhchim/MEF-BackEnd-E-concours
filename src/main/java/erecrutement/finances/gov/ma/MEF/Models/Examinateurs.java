package erecrutement.finances.gov.ma.MEF.Models;

public class Examinateurs {
    private int id;
    private String email;

    public Examinateurs(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
