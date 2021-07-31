package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Profils implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfil;

    private String type;
    private String intitled;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "idConcours")
    private Concours concours;



    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "GradesDesProfils",
    joinColumns = @JoinColumn(name = "idProfil"),
    inverseJoinColumns = @JoinColumn(name = "id_Grade"))
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

    //@JsonBackReference
    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
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
