package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Centres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCentre;

    private double longitude;
    private double latitude;
    @NotBlank(message = "Vous devez specifier L'adresse du centre")
    @Pattern(regexp = "([A-Za-z0-9À-ÿ ])*",message = "L'adresse contient seulement la localisation du centre et ou le numero")
    private String adresse;
    @NotBlank(message = "Veuillez saisir la salle du centre")
    @Pattern(regexp = "([A-Za-z0-9À-ÿ ])*",message = "La salle doit contenir seulement des numeros et chaines de caracteres")
    private String salle;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CentresConcours",
            joinColumns = @JoinColumn(name = "idCentre"),
            inverseJoinColumns = @JoinColumn(name = "idConcours"))
    private List<Concours> concours = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "idVille")
    private Ville ville;

    public Centres(double longitude, double latitude, String adresse, String salle) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse = adresse;
        this.salle = salle;
    }

    public Centres(){

    }

    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public List<Concours> getConcours() {
        return concours;
    }

    public void setConcours(List<Concours> concours) {
        this.concours = concours;
    }

   // @JsonBackReference
    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Centres{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", adresse='" + adresse + '\'' +
                ", salle='" + salle + '\'' +
                '}';
    }

}
