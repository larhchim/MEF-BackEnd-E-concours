package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

@Entity
public class Resultats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultats;

    private Date dateConcours;
    private int nombrePostes;

    @Lob
    private Clob candidatsConvoques;

    @Lob
    private Clob resultatsEcrit;

    @Lob
    private Clob resultatsDefenitifs;

    @OneToOne(targetEntity = Concours.class)
    private Concours concours;

    public Resultats(Date dateConcours, int nombrePostes, Clob candidatsConvoques, Clob resultatsEcrit, Clob resultatsDefenitifs) {
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

    public Clob getCandidatsConvoques() {
        return candidatsConvoques;
    }

    public void setCandidatsConvoques(Clob candidatsConvoques) {
        this.candidatsConvoques = candidatsConvoques;
    }

    public Clob getResultatsEcrit() {
        return resultatsEcrit;
    }

    public void setResultatsEcrit(Clob resultatsEcrit) {
        this.resultatsEcrit = resultatsEcrit;
    }

    public Clob getResultatsDefenitifs() {
        return resultatsDefenitifs;
    }

    public void setResultatsDefenitifs(Clob resultatsDefenitifs) {
        this.resultatsDefenitifs = resultatsDefenitifs;
    }

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
