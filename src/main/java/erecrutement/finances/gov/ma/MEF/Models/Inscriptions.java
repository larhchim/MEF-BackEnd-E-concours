package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Inscriptions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscription;

    @Pattern(regexp = "[A-Za-z0-9À-ÿ ]+", message = "*Entrez un Nom Valide*")
    @NotNull(message = "*Veuillez saisir votre nom complet*")
    @NotBlank(message = "*Le nom est un champ obligatoire*")
    private String nomComplet;



    @Pattern(regexp = "[A-Za-z0-9À-ÿ.' ]+", message = "*Entrez une adresse Valide*")
    @NotBlank(message = "*Adresse est un champ obligatoire*")
    private String adresse;



    @Pattern(regexp = "[A-Za-z0-9 ]+", message = "*Entrez Numero CIN Valide ex: AD258754*")
    @NotNull(message = "*Veuillez saisir votre CIN*")
    @NotBlank(message = "*CIN est un champ obligatoire*")
    private String cin;



    @Pattern(regexp = "[+0-9]+", message = "*Entrez un numero de telephone Valide ex: +21264015900*")
    @NotNull(message = "*Veuillez saisir votre telephone*")
    @NotBlank(message = "*Telephone est un champ obligatoire*")
    private String telephone;



    @Pattern(regexp = "[0-9]+", message = "*Entrez un code postal Valide ex:120200*")
    @NotNull(message = "*Veuillez saisir votre code postal*")
    @NotBlank(message = "*Code Postal est un champ obligatoire*")
    private String codePostal;



    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "Entrez un email valide")
    @NotNull(message = "*Veuillez saisir votre adresse email*")
    @NotBlank(message = "*Adresse Email est un champ obligatoire*")
    private String adresseEmail;



    @Pattern(regexp = "[A-Za-z0-9'.À-ÿ ]+", message = "*Entrez une ville Valide*")
    @NotNull(message = "*Veuillez specifier votre ville d'origine*")
    @NotBlank(message = "*Ville est un champ obligatoire*")
    private String ville;



    @Pattern(regexp = "[A-Za-z0-9.'À-ÿ ]+", message = "*Entrez une specialité Valide*")
    @NotNull(message = "*Veuillez saisir votre specialité*")
    @NotBlank(message = "*Specialité est un champ obligatoire*")
    private String specialite;



    @NotNull(message = "*Le champ Annee obtention diplome est obligatoire*")
    private Integer anneeObtentionDiplome;


    @NotNull(message = "*Le champ Age Candidat est obligatoire*")
    private Integer ageCandidat;



    @Pattern(regexp = "[A-Za-z0-9À-ÿ'. ]+", message = "*Entrez un diplome Valide*")
    @NotNull(message = "*Veuillez saisir le diplome obtenue*")
    @NotBlank(message = "*Diplome Obtenue est un champ obligatoire*")
    private String diplomeObtenue;


    @Pattern(regexp = "[A-Za-z0-9À-ÿ.' ]+", message = "*Entrez un etablissement Valide*")
    @NotNull(message = "*Le champ etablissement est obligatoire*")
    @NotBlank(message = "*Etablissement est un champ obligatoire*")
    private String etablissement;



    private Boolean status;

    @Temporal(TemporalType.DATE)
    private Date dateCandidature;


    private Boolean etatCandidature;


    @NotNull(message = "*Le champ Mot de passe est obligatoire*")
    @NotBlank(message = "*Mot de Passe est un champ obligatoire*")
    private String motDePasse;

    @NotNull(message = "*Le champ Mot de passe est obligatoire*")
    @NotBlank(message = "*Mot de Passe est un champ obligatoire*")
    private String remotDePasse;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idConcours")
    @JsonIgnore
    private Concours concours;

    @OneToMany(mappedBy = "inscription")
    private List<HistoriqueChangementStatus> historiquestatusChange = new ArrayList<>();

    @OneToMany(mappedBy = "inscription")

    private List<FichiersJoints> fichiers = new ArrayList<>();


    public Inscriptions(String nomComplet, String adresse, String cin, String telephone, String codePostal, String adresseEmail, String ville, String specialite, Integer anneeObtentionDiplome, String diplomeObtenue, String etablissement, Boolean status, Date dateCandidature, Boolean etatCandidature, String motDePasse,String remotDePasse,int age) {
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
        this.remotDePasse = remotDePasse;
        this.ageCandidat = age;
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

    public Integer getAnneeObtentionDiplome() {
        return anneeObtentionDiplome;
    }

    public void setAnneeObtentionDiplome(Integer anneeObtentionDiplome) {
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

    public String getRemotDePasse() {
        return remotDePasse;
    }

    public void setRemotDePasse(String remotDePasse) {
        this.remotDePasse = remotDePasse;
    }

    // @JsonBackReference
   public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

   // @JsonManagedReference
    public List<HistoriqueChangementStatus> getHistoriquestatusChange() {
        return historiquestatusChange;
    }

    public void setHistoriquestatusChange(List<HistoriqueChangementStatus> historiquestatusChange) {
        this.historiquestatusChange = historiquestatusChange;
    }

  //  @JsonManagedReference
    public List<FichiersJoints> getFichiers() {
        return fichiers;
    }

    public void setFichiers(List<FichiersJoints> fichiers) {
        this.fichiers = fichiers;
    }


    public Integer getAgeCandidat() {
        return ageCandidat;
    }

    public void setAgeCandidat(Integer ageCandidat) {
        this.ageCandidat = ageCandidat;
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
