package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Resultats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultats;

    @Temporal(TemporalType.DATE)
    private Date dateConcours;

    private int nombrePostes;

    private String candidatsConvoques;

    private String resultatsEcrit;

    private String resultatsDefenitifs;

    @OneToOne(targetEntity = Concours.class,cascade=CascadeType.ALL)
    @JsonIgnore
    private Concours concours;

    public Resultats(Date dateConcours, int nombrePostes, String candidatsConvoques, String resultatsEcrit, String resultatsDefenitifs) {
        this.dateConcours = dateConcours;
        this.nombrePostes = nombrePostes;
        this.candidatsConvoques = candidatsConvoques;
        this.resultatsEcrit = resultatsEcrit;
        this.resultatsDefenitifs = resultatsDefenitifs;
    }

    public Resultats(){

    }

    public int getIdResultats() {
        return idResultats;
    }

    public void setIdResultats(int idResultats) {
        this.idResultats = idResultats;
    }

    public Date getDateConcours() {
        return dateConcours;
    }

    public void setDateConcours(Date dateConcours) {
        this.dateConcours = dateConcours;
    }

    public int getNombrePostes() {
        return nombrePostes;
    }

    public void setNombrePostes(int nombrePostes) {
        this.nombrePostes = nombrePostes;
    }

    public String getCandidatsConvoques() {
        return candidatsConvoques;
    }

    public void setCandidatsConvoques(String candidatsConvoques) {
        this.candidatsConvoques = candidatsConvoques;
    }

    public String getResultatsEcrit() {
        return resultatsEcrit;
    }

    public void setResultatsEcrit(String resultatsEcrit) {
        this.resultatsEcrit = resultatsEcrit;
    }

    public String getResultatsDefenitifs() {
        return resultatsDefenitifs;
    }

    public void setResultatsDefenitifs(String resultatsDefenitifs) {
        this.resultatsDefenitifs = resultatsDefenitifs;
    }

   // @JsonBackReference
    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    @Override
    public String toString() {
        return "Resultats{" +
                "dateConcours=" + dateConcours +
                ", nombrePostes=" + nombrePostes +
                ", candidatsConvoques=" + candidatsConvoques +
                ", resultatsEcrit=" + resultatsEcrit +
                ", resultatsDefenitifs=" + resultatsDefenitifs +
                '}';
    }
}
