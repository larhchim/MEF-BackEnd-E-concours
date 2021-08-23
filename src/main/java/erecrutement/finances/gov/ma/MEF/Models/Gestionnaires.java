package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Gestionnaires implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GestionnaireId")
    private int idGestionnaire;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String motDePasse;
    private Boolean etatcCompte;
    private Boolean isAdministrator;
    private int habilitation;
    @Column(unique = true)
    @NotNull(message = "Log in must be valid")
    private String login;

    @ManyToMany()
    @JoinTable(name = "DirectionsGestionnaires",
            joinColumns = @JoinColumn(name = "GestionnaireId"),
            inverseJoinColumns = @JoinColumn(name = "DirectionId"))
    private List<Directions> directions = new ArrayList<>();

    @OneToMany(mappedBy = "gestionnaire")
    private List<HistoriqueGestionnaire> historyGest = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();


    public Gestionnaires(String motDePasse, Boolean etatcCompte, Boolean isAdministrator, int habilitation, String login) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
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

  //  @JsonManagedReference
    public List<HistoriqueGestionnaire> getHistoryGest() {
        return historyGest;
    }

    public void setHistoryGest(List<HistoriqueGestionnaire> historyGest) {
        this.historyGest = historyGest;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
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
