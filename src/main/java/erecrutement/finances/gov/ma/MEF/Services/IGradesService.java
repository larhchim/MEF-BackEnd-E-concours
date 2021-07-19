package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Grades;

import java.util.List;

public interface IGradesService {

    public List<Grades> TousLesGrades();

    public Grades addGrades(Grades g);

    public Grades ModifyGrades(Grades g);

    public Grades supprimerGrades(Grades g);

    public Grades leGrade(int id);
}
