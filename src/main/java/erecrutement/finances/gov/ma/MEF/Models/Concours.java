package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Concours implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idConcours;

    @Temporal(TemporalType.DATE)
    private Date dateConcours;

    @Temporal(TemporalType.DATE)
    private Date datePassage;

    private Date dateLimiteConcours;

    private String intitled;
    private Boolean etat;
    private int nombrePostes;


    private String exigences;

    @ManyToOne()
    @JoinColumn(name = "DirectionId")
    private Directions direction;

    @OneToMany(mappedBy = "concours")
    private List<Inscriptions> inscriptions = new ArrayList<>();

    @OneToOne(targetEntity = Resultats.class,cascade=CascadeType.ALL)
    private Resultats resultats;

    @ManyToMany()
    @JoinTable(name = "CentresConcours",
    joinColumns = @JoinColumn(name = "idConcours"),
    inverseJoinColumns = @JoinColumn(name = "idCentre"))
    private List<Centres> centres = new ArrayList<>();


    @ManyToMany()
    @JoinTable(name = "ProfilsConcours",
    joinColumns = @JoinColumn(name = "idConcours"),
    inverseJoinColumns = @JoinColumn(name = "idProfil"))
    private List<Profils> profils = new ArrayList<>();

    public Concours(Date dateConcours, Date dateLimiteConcours, String intitled, Boolean etat, int nombrePostes, String exigences) {
        this.dateConcours = dateConcours;
        this.dateLimiteConcours = dateLimiteConcours;
        this.intitled = intitled;
        this.etat = etat;
        this.nombrePostes = nombrePostes;
        this.exigences = exigences;
    }

    public Concours(){

    }

    public int getIdConcours() {
        return idConcours;
    }

    public void setIdConcours(int idConcours) {
        this.idConcours = idConcours;
    }

    public Date getDateConcours() {
        return dateConcours;
    }

    public void setDateConcours(Date dateConcours) {
        this.dateConcours = dateConcours;
    }

    public Date getDateLimiteConcours() {
        return dateLimiteConcours;
    }

    public void setDateLimiteConcours(Date dateLimiteConcours) {
        this.dateLimiteConcours = dateLimiteConcours;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public int getNombrePostes() {
        return nombrePostes;
    }

    public void setNombrePostes(int nombrePostes) {
        this.nombrePostes = nombrePostes;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

   // @JsonBackReference
    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

   // @JsonManagedReference
    public List<Inscriptions> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscriptions> inscriptions) {
        this.inscriptions = inscriptions;
    }

    //@JsonBackReference
    public Resultats getResultats() {
        return resultats;
    }

    public void setResultats(Resultats resultats) {
        this.resultats = resultats;
    }

    public List<Centres> getCentres() {
        return centres;
    }

    public void setCentres(List<Centres> centres) {
        this.centres = centres;
    }

    //@JsonManagedReference
    public List<Profils> getProfils() {
        return profils;
    }

    public void setProfils(List<Profils> profils) {
        this.profils = profils;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }

    @Override
    public String toString() {
        return "Concours{" +
                "dateConcours=" + dateConcours +
                ", dateLimiteConcours=" + dateLimiteConcours +
                ", intitled='" + intitled + '\'' +
                ", etat=" + etat +
                ", nombrePostes=" + nombrePostes +
                ", exigences=" + exigences +
                '}';
    }
}
