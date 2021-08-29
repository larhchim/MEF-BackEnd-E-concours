package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Profils implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfil;

    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+", message = "Entrez un profil valide")
    private String type;
    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+", message = "Entrez une specialité de profil")
    private String intitled;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "ProfilsConcours",
    joinColumns = @JoinColumn(name = "idProfil"),
    inverseJoinColumns = @JoinColumn(name = "idConcours"))
    private List<Concours> concours = new ArrayList<>();



    @ManyToMany(mappedBy = "profils")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    /*@JoinTable(name = "GradesDesProfils",
    joinColumns = @JoinColumn(name = "idProfil"),
    inverseJoinColumns = @JoinColumn(name = "id_Grade"))*/
    private List<Grades> grades = new ArrayList<>();

    public Profils(String type, String intitled) {
        this.type = type;
        this.intitled = intitled;
    }

    public Profils(){

    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntitled() {
        return intitled;
    }

    public void setIntitled(String intitled) {
        this.intitled = intitled;
    }

    public List<Concours> getConcours() {
        return concours;
    }

    public void setConcours(List<Concours> concours) {
        this.concours = concours;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }



    @Override
    public String toString() {
        return "Profils{" +
                "type='" + type + '\'' +
                ", intitled='" + intitled + '\'' +
                '}';
    }

}
