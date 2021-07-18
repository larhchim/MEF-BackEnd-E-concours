package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gestionnaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GestionnaireId")
    private int idGestionnaire;
    private String motDePasse;
    private Boolean etatcCompte;
    private Boolean isAdministrator;
    private int habilitation;
    private int login;

    @ManyToMany()
    @JoinTable(name = "DirectionsGestionnaires",
            joinColumns = @JoinColumn(name = "GestionnaireId"),
            inverseJoinColumns = @JoinColumn(name = "DirectionId"))
    private List<Directions> directions = new ArrayList<>();

    @OneToMany(mappedBy = "gestionnaire")
    private List<HistoriqueGestionnaire> historyGest = new ArrayList<>();


    public Gestionnaires(String motDePasse, Boolean etatcCompte, Boolean isAdministrator, int habilitation, int login) {
        this.motDePasse = motDePasse;
        this.etatcCompte = etatcCompte;
        this.isAdministrator = isAdministrator;
        this.habilitation = habilitation;
        this.login = login;
    }

    public Gestionnaires(){

    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Boolean getEtatcCompte() {
        return etatcCompte;
    }

    public void setEtatcCompte(Boolean etatcCompte) {
        this.etatcCompte = etatcCompte;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }

    public int getHabilitation() {
        return habilitation;
    }

    public void setHabilitation(int habilitation) {
        this.habilitation = habilitation;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public List<Directions> getDirections() {
        return directions;
    }

    public void setDirections(List<Directions> directions) {
        this.directions = directions;
    }

    public List<HistoriqueGestionnaire> getHistoryGest() {
        return historyGest;
    }

    public void setHistoryGest(List<HistoriqueGestionnaire> historyGest) {
        this.historyGest = historyGest;
    }

    @Override
    public String toString() {
        return "Gestionnaires{" +
                "motDePasse='" + motDePasse + '\'' +
                ", etatcCompte=" + etatcCompte +
                ", isAdministrator=" + isAdministrator +
                ", habilitation=" + habilitation +
                ", login=" + login +
                '}';
    }
}
