package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class AgentsDeSupport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgentSupport;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Le mot de passe doit etre sous cette forme:numeros lettres eb miniscule lettres en majuscule des caracteres speciaux (@#$%^&+=) vous ne pouvez pas faire un espace")
    private String motDePasse;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "Entrez un email valide")
    private String login;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "DirectionId")
    @JsonIgnore

    private Directions direction;

    @OneToMany(mappedBy = "agentsSupports")
    private List<Reclamations> reclamations = new ArrayList<>();

    public AgentsDeSupport(String motDePasse, String login) {
        this.motDePasse = motDePasse;
        this.login = login;
    }

    public AgentsDeSupport(){

    }

    public int getIdAgentSupport() {
        return idAgentSupport;
    }

    public void setIdAgentSupport(int idAgentSupport) {
        this.idAgentSupport = idAgentSupport;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //@JsonBackReference
    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    //@JsonManagedReference
    public List<Reclamations> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamations> reclamations) {
        this.reclamations = reclamations;
    }

    @Override
    public String toString() {
        return "AgentsDeSupport{" +
                "motDePasse='" + motDePasse + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

}
