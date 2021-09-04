package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "[A-Za-z0-9.'-_%À-ÿ ]+", message = "Entrez une chaine de caracteres valide")
    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idInscription")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Inscriptions inscription;


    private Boolean refus;

    private Boolean acceptation;

    private Boolean instance;

    private String examinateur;



    public HistoriqueChangementStatus(Date dateAction, @Pattern(regexp = "[A-Za-z0-9.'-_%À-ÿ ]+", message = "Entrez une chaine de caracteres valide") String description, Inscriptions inscription, Boolean refus, Boolean acceptation, Boolean instance,String examinateur) {
        this.dateAction = dateAction;
        this.description = description;
        this.inscription = inscription;
        this.refus = refus;
        this.acceptation = acceptation;
        this.instance = instance;
        this.examinateur =examinateur;
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

    public Boolean getRefus() {
        return refus;
    }

    public void setRefus(Boolean refus) {
        this.refus = refus;
    }

    public Boolean getAcceptation() {
        return acceptation;
    }

    public void setAcceptation(Boolean acceptation) {
        this.acceptation = acceptation;
    }

    public Boolean getInstance() {
        return instance;
    }

    public void setInstance(Boolean instance) {
        this.instance = instance;
    }

    public String getExaminateur() {
        return examinateur;
    }

    public void setExaminateur(String examinateur) {
        this.examinateur = examinateur;
    }

    @Override
    public String toString() {
        return "HistoriqueChangementStatus{" +
                "dateAction=" + dateAction +
                ", description='" + description + '\'' +
                '}';
    }
}
