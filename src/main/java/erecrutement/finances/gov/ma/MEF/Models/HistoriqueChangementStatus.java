package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class HistoriqueChangementStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistoryStatus;

    @Temporal(TemporalType.DATE)
    private Date dateAction;


    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idInscription")
    @JsonIgnore
    private Inscriptions inscription;

    public HistoriqueChangementStatus(Date dateAction, String description) {
        this.dateAction = dateAction;
        this.description = description;
    }

    public HistoriqueChangementStatus(){

    }

    public int getIdHistoryStatus() {
        return idHistoryStatus;
    }

    public void setIdHistoryStatus(int idHistoryStatus) {
        this.idHistoryStatus = idHistoryStatus;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   // @JsonBackReference
    public Inscriptions getInscription() {
        return inscription;
    }

    public void setInscription(Inscriptions inscription) {
        this.inscription = inscription;
    }

    @Override
    public String toString() {
        return "HistoriqueChangementStatus{" +
                "dateAction=" + dateAction +
                ", description='" + description + '\'' +
                '}';
    }
}
