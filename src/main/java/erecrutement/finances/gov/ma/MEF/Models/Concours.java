package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Concours {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idConcours;

    private Date dateConcours;
    private Date dateLimiteConcours;
    private String intitled;
    private Boolean etat;
    private int nombrePostes;

    @Lob
    private Clob exigences;

    @ManyToOne()
    @JoinColumn(name = "DirectionId")
    private Directions direction;

    @OneToMany(mappedBy = "concours")
    private List<Inscriptions> inscriptions = new ArrayList<>();

    @OneToOne(targetEntity = Resultats.class)
    private Resultats resultats;

    @ManyToMany
    @JoinTable(name = "CentresConcours",
    joinColumns = @JoinColumn(name = "idConcours"),
    inverseJoinColumns = @JoinColumn(name = "idCentre"))
    private List<Centres> centres = new ArrayList<>();

    @OneToMany(mappedBy = "concours")
    private List<Profils> profils = new ArrayList<>();

    public Concours(Date dateConcours, Date dateLimiteConcours, String intitled, Boolean etat, int nombrePostes, Clob exigences) {
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

    public Clob getExigences() {
        return exigences;
    }

    public void setExigences(Clob exigences) {
        this.exigences = exigences;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public List<Inscriptions> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscriptions> inscriptions) {
        this.inscriptions = inscriptions;
    }

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

    public List<Profils> getProfils() {
        return profils;
    }

    public void setProfils(List<Profils> profils) {
        this.profils = profils;
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
