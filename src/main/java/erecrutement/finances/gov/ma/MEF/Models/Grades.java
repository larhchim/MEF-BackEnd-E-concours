package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Grade")
    private int id;

    private String intitledGrade;


    @ManyToMany
    @JoinTable(name = "GradesDesProfils",
            joinColumns = @JoinColumn(name = "id_Grade"),
            inverseJoinColumns = @JoinColumn(name = "idProfil"))
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
