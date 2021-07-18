package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;

@Entity
public class FichiersJoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFichier;

    private String nomFichier;

    @Lob
    private Clob fichier;

    @ManyToOne
    @JoinColumn(name = "idInscription")
    private Inscriptions inscription;

    public FichiersJoints(String nomFichier, Clob fichier) {
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

    public Clob getFichier() {
        return fichier;
    }

    public void setFichier(Clob fichier) {
        this.fichier = fichier;
    }

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
