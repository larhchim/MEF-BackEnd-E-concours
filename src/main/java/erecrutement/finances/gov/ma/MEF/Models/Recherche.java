package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Recherche {

    private int idConcours;
    private Date dateLimiteConcours;
    @Temporal(TemporalType.DATE)
    private Date datePassage;
    private String intitled;
    private int nombrePostes;
    private String exigences;
    private Resultats resultats;

    public Recherche(int idConcours, Date dateLimiteConcours, Date datePassage, String intitled, int nombrePostes, String exigences, Resultats resultats) {
        this.idConcours = idConcours;
        this.dateLimiteConcours = dateLimiteConcours;
        this.datePassage = datePassage;
        this.intitled = intitled;
        this.nombrePostes = nombrePostes;
        this.exigences = exigences;
        this.resultats =resultats;
    }


    public Resultats getResultats() {
        return resultats;
    }

    public void setResultats(Resultats resultats) {
        this.resultats = resultats;
    }

    public int getIdConcours() {
        return idConcours;
    }

    public void setIdConcours(int idConcours) {
        this.idConcours = idConcours;
    }

    public Date getDateLimiteConcours() {
        return dateLimiteConcours;
    }

    public void setDateLimiteConcours(Date dateLimiteConcours) {
        this.dateLimiteConcours = dateLimiteConcours;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
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
}
