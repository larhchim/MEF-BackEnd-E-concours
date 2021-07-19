package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Inscriptions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscription;

    private String nomComplet;
    private String adresse;
    private String cin;
    private String telephone;
    private String codePostal;
    private String adresseEmail;
    private String ville;
    private String specialite;
    private int anneeObtentionDiplome;
    private String diplomeObtenue;
    private String etablissement;
    private Boolean status;
    private Date dateCandidature;
    private Boolean etatCandidature;
    private String motDePasse;

    @ManyToOne()
    @JoinColumn(name = "idConcours")
    private Concours concours;

    @OneToMany(mappedBy = "inscription")
    private List<HistoriqueChangementStatus> historiquestatusChange = new ArrayList<>();

    @OneToMany(mappedBy = "inscription")
    private List<FichiersJoints> fichiers = new ArrayList<>();


    public Inscriptions(String nomComplet, String adresse, String cin, String telephone, String codePostal, String adresseEmail, String ville, String specialite, int anneeObtentionDiplome, String diplomeObtenue, String etablissement, Boolean status, Date dateCandidature, Boolean etatCandidature, String motDePasse) {
        this.nomComplet = nomComplet;
        this.adresse = adresse;
        this.cin = cin;
        this.telephone = telephone;
        this.codePostal = codePostal;
        this.adresseEmail = adresseEmail;
        this.ville = ville;
        this.specialite = specialite;
        this.anneeObtentionDiplome = anneeObtentionDiplome;
        this.diplomeObtenue = diplomeObtenue;
        this.etablissement = etablissement;
        this.status = status;
        this.dateCandidature = dateCandidature;
        this.etatCandidature = etatCandidature;
        this.motDePasse = motDePasse;
    }

    public Inscriptions(){

    }

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getAnneeObtentionDiplome() {
        return anneeObtentionDiplome;
    }

    public void setAnneeObtentionDiplome(int anneeObtentionDiplome) {
        this.anneeObtentionDiplome = anneeObtentionDiplome;
    }

    public String getDiplomeObtenue() {
        return diplomeObtenue;
    }

    public void setDiplomeObtenue(String diplomeObtenue) {
        this.diplomeObtenue = diplomeObtenue;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Date dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public Boolean getEtatCandidature() {
        return etatCandidature;
    }

    public void setEtatCandidature(Boolean etatCandidature) {
        this.etatCandidature = etatCandidature;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    public List<HistoriqueChangementStatus> getHistoriquestatusChange() {
        return historiquestatusChange;
    }

    public void setHistoriquestatusChange(List<HistoriqueChangementStatus> historiquestatusChange) {
        this.historiquestatusChange = historiquestatusChange;
    }

    public List<FichiersJoints> getFichiers() {
        return fichiers;
    }

    public void setFichiers(List<FichiersJoints> fichiers) {
        this.fichiers = fichiers;
    }

    @Override
    public String toString() {
        return "Inscriptions{" +
                "nomComplet='" + nomComplet + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cin='" + cin + '\'' +
                ", telephone='" + telephone + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", adresseEmail='" + adresseEmail + '\'' +
                ", ville='" + ville + '\'' +
                ", specialite='" + specialite + '\'' +
                ", anneeObtentionDiplome=" + anneeObtentionDiplome +
                ", diplomeObtenue='" + diplomeObtenue + '\'' +
                ", etablissement='" + etablissement + '\'' +
                ", status=" + status +
                ", dateCandidature=" + dateCandidature +
                ", etatCandidature=" + etatCandidature +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
