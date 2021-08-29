package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Reclamations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamation;

    private int numeroCandidat;
    private int numeroConcours;
    private int numeroGestionnaire;
    @Pattern(regexp = "[A-Za-z0-9.'-_%À-ÿ ]+", message = "Entrez une dizaine de chaines de caractéres Valide")
    private String intitled;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idAgentSupport")
    @JsonIgnore
    private AgentsDeSupport agentsSupports;

    public Reclamations(int numeroCandidat, int numeroConcours, int numeroGestionnaire, String intitled) {
        this.numeroCandidat = numeroCandidat;
        this.numeroConcours = numeroConcours;
        this.numeroGestionnaire = numeroGestionnaire;
        this.intitled = intitled;
    }

    public Reclamations(){

    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public int getNumeroCandidat() {
        return numeroCandidat;
    }

    public void setNumeroCandidat(int numeroCandidat) {
        this.numeroCandidat = numeroCandidat;
    }

    public int getNumeroConcours() {
        return numeroConcours;
    }

    public void setNumeroConcours(int numeroConcours) {
        this.numeroConcours = numeroConcours;
    }

    public int getNumeroGestionnaire() {
        return numeroGestionnaire;
    }

    public void setNumeroGestionnaire(int numeroGestionnaire) {
        this.numeroGestionnaire = numeroGestionnaire;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

   // @JsonBackReference
    public AgentsDeSupport getAgentsSupports() {
        return agentsSupports;
    }

    public void setAgentsSupports(AgentsDeSupport agentsSupports) {
        this.agentsSupports = agentsSupports;
    }

    @Override
    public String toString() {
        return "Reclamations{" +
                "numeroCandidat=" + numeroCandidat +
                ", numeroConcours=" + numeroConcours +
                ", numeroGestionnaire=" + numeroGestionnaire +
                ", intitled='" + intitled + '\'' +
                '}';
    }
}
