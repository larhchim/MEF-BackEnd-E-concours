package erecrutement.finances.gov.ma.MEF.Models;

public class PDF {
    private int NumeroInscription;
    private String NomComplet;
    private String Email;
    private String Cin;
    private String Concours;
    private int numeroc;

    public PDF(int numeroInscription, String nomComplet, String email, String cin, String concours,int n) {
        NumeroInscription = numeroInscription;
        NomComplet = nomComplet;
        Email = email;
        Cin = cin;
        Concours = concours;
        numeroc =n;
    }

    public int getNumeroInscription() {
        return NumeroInscription;
    }

    public void setNumeroInscription(int numeroInscription) {
        NumeroInscription = numeroInscription;
    }

    public String getNomComplet() {
        return NomComplet;
    }

    public void setNomComplet(String nomComplet) {
        NomComplet = nomComplet;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getConcours() {
        return Concours;
    }

    public void setConcours(String concours) {
        Concours = concours;
    }

    public int getNumeroc() {
        return numeroc;
    }

    public void setNumeroc(int numeroc) {
        this.numeroc = numeroc;
    }

    @Override
    public String toString() {
        return "PDF{" +
                "NumeroInscription=" + NumeroInscription +
                ", NomComplet='" + NomComplet + '\'' +
                ", Email='" + Email + '\'' +
                ", Cin='" + Cin + '\'' +
                ", Concours='" + Concours + '\'' +
                '}';
    }
}
