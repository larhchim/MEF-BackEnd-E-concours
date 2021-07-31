package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Ville implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVille;

    private double latitudeVille;
    private double longitudeVille;
    private String nomVille;

    @OneToMany(mappedBy = "ville")
    @JsonIgnore
    private List<Centres> centres;

    public Ville(double latitudeVille, double longitudeVille, String nomVille) {
        this.latitudeVille = latitudeVille;
        this.longitudeVille = longitudeVille;
        this.nomVille = nomVille;
    }

    public Ville(){

    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public double getLatitudeVille() {
        return latitudeVille;
    }

    public void setLatitudeVille(double latitudeVille) {
        this.latitudeVille = latitudeVille;
    }

    public double getLongitudeVille() {
        return longitudeVille;
    }

    public void setLongitudeVille(double longitudeVille) {
        this.longitudeVille = longitudeVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

   // @JsonManagedReference
    public List<Centres> getCentres() {
        return centres;
    }

    public void setCentres(List<Centres> centres) {
        this.centres = centres;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "latitudeVille=" + latitudeVille +
                ", longitudeVille=" + longitudeVille +
                ", nomVille='" + nomVille + '\'' +
                '}';
    }

}
