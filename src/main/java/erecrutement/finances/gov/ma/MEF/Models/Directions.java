package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Directions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DirectionId")
    private int id;

    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+",message = "Entrez une chaine de caracteres valide")
    private String fonction;
    @Lob
    @Column(name="description", length=99999)
    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+", message = "Entrez une chaine de caracteres valide")
    private String description;
    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+", message = "Entrez une chaine de caracteres valide")
    private String intitled;

    @Pattern(regexp = "[A-Za-z0-9.'-_%À-ÿ ]+", message = "Entrez une chaine de caracteres valide")
    private String logo;

    @Pattern(regexp = "[A-Za-zÀ-ÿ' ]+", message = "Entrez un Nom valide")
    private String nom;

    @OneToMany(mappedBy = "direction",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Concours> cnc;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "DirectionsGestionnaires",
    joinColumns = @JoinColumn(name = "DirectionId"),
    inverseJoinColumns = @JoinColumn(name = "GestionnaireId"))
    private List<Gestionnaires> gest = new ArrayList<>();

    @OneToMany(mappedBy = "direction")
    private List<AgentsDeSupport> agentsDeSupports = new ArrayList<>();

    public Directions(String fonction, String description, String intitled, String logo, String nom) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   // @JsonManagedReference
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

   // @JsonManagedReference
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
