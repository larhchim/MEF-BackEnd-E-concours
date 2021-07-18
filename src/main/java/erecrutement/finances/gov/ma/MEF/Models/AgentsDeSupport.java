package erecrutement.finances.gov.ma.MEF.Models;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AgentsDeSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgentSupport;

    private String motDePasse;
    private String login;

    @ManyToOne
    @JoinColumn(name = "DirectionId")
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

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

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
