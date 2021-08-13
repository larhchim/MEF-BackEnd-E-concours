package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Grades;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IGradesService {

    public List<Grades> TousLesGrades();

    public Optional<Grades> addGrades(Grades g);

    public Grades ModifyGrades(Grades g);

    public Grades supprimerGrades(Grades g);

    public Grades leGrade(int id);

    public Page<Grades> chercher(String mc, int page, int size);

    public List<Profils> ProfilsManqueGrade(int id);

}
