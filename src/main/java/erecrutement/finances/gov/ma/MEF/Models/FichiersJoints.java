package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class FichiersJoints implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFichier;

    private String nomFichier;

    private String fichier;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idInscription")
    @JsonIgnore
    private Inscriptions inscription;

    public FichiersJoints(String nomFichier, String fichier) {
        this.nomFichier = nomFichier;
        this.fichier = fichier;
    }

    public FichiersJoints(){

    }

    public int getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(int idFichier) {
        this.idFichier = idFichier;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
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
        return "FichiersJoints{" +
                "nomFichier='" + nomFichier + '\'' +
                ", fichier=" + fichier +
                '}';
    }
}
