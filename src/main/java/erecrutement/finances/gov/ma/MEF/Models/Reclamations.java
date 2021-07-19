package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reclamations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamation;

    private int numeroCandidat;
    private int numeroConcours;
    private int numeroGestionnaire;
    private String intitled;

    @ManyToOne
    @JoinColumn(name = "idAgentSupport")
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
