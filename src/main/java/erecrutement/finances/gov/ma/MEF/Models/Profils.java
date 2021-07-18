package erecrutement.finances.gov.ma.MEF.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfil;

    private String type;
    private String intitled;

    @ManyToOne()
    @JoinColumn(name = "idConcours")
    private Concours concours;



    @ManyToMany
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
