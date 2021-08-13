package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class HistoriqueGestionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorique;

    private String type;
    private String intitled;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateAction;

    private String tache;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "GestionnaireId")
    @JsonIgnore
    private Gestionnaires gestionnaire;

    public HistoriqueGestionnaire(String type, String intitled, String description, Date dateAction, String tache) {
        this.type = type;
        this.intitled = intitled;
        this.description = description;
        this.dateAction = dateAction;
        this.tache = tache;
    }

    public HistoriqueGestionnaire(){

    }

    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

   // @JsonBackReference
    public Gestionnaires getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Gestionnaires gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    @Override
    public String toString() {
        return "HistoriqueGestionnaire{" +
                "type='" + type + '\'' +
                ", intitled='" + intitled + '\'' +
                ", description='" + description + '\'' +
                ", dateAction=" + dateAction +
                ", tache='" + tache + '\'' +
                '}';
    }
}
