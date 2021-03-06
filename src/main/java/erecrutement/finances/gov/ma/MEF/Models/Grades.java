package erecrutement.finances.gov.ma.MEF.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Grades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Grade")
    private int id;

    @Pattern(regexp = "[A-Za-z0-9'À-ÿ ]+", message = "Entrez une chaine de caracteres valide")
    private String intitledGrade;

    @ManyToMany()
    @JoinTable(name = "GradesDesProfils",
            joinColumns = @JoinColumn(name = "id_Grade"),
            inverseJoinColumns = @JoinColumn(name = "idProfil"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_Grade","idProfil"} ))
    private List<Profils> profils = new ArrayList<>();

    public Grades(String intitledGrade) {
        this.intitledGrade = intitledGrade;
    }

    public Grades(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitledGrade() {
        return intitledGrade;
    }

    public void setIntitledGrade(String intitledGrade) {
        this.intitledGrade = intitledGrade;
    }

    public List<Profils> getProfils() {
        return profils;
    }

    public void setProfils(List<Profils> profils) {
        this.profils = profils;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "intitledGrade='" + intitledGrade + '\'' +
                '}';
    }

}
