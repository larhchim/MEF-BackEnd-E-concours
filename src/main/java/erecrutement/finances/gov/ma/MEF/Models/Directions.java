package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Directions implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DirectionId")
    private int id;

    private String fonction;
    private String description;
    private String intitled;

    @Lob
    private Blob logo;

    private String nom;

    @OneToMany(mappedBy = "direction")
    private List<Concours> cnc;

    @ManyToMany()
    @JoinTable(name = "DirectionsGestionnaires",
    joinColumns = @JoinColumn(name = "DirectionId"),
    inverseJoinColumns = @JoinColumn(name = "GestionnaireId"))
    private List<Gestionnaires> gest = new ArrayList<>();

    @OneToMany(mappedBy = "direction")
    private List<AgentsDeSupport> agentsDeSupports = new ArrayList<>();

    public Directions(String fonction, String description, String intitled, Blob logo, String nom) {
        this.fonction = fonction;
        this.description = description;
        this.intitled = intitled;
        this.logo = logo;
        this.nom = nom;
    }

    public Directions(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Concours> getCnc() {
        return cnc;
    }

    public void setCnc(List<Concours> cnc) {
        this.cnc = cnc;
    }

    public List<Gestionnaires> getGest() {
        return gest;
    }

    public void setGest(List<Gestionnaires> gest) {
        this.gest = gest;
    }

    public List<AgentsDeSupport> getAgentsDeSupports() {
        return agentsDeSupports;
    }

    public void setAgentsDeSupports(List<AgentsDeSupport> agentsDeSupports) {
        this.agentsDeSupports = agentsDeSupports;
    }

    @Override
    public String toString() {
        return "Directions{" +
                "fonction='" + fonction + '\'' +
                ", description='" + description + '\'' +
                ", intitled='" + intitled + '\'' +
                ", logo=" + logo +
                ", nom='" + nom + '\'' +
                '}';
    }
}
