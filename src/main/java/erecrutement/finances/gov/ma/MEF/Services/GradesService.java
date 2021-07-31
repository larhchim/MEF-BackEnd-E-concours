package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.GradeDAO;
import erecrutement.finances.gov.ma.MEF.Models.Grades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradesService implements IGradesService{

    private GradeDAO grd;

    @Autowired
    public void setGrd(GradeDAO grd) {
        this.grd = grd;
    }

    @Override
    public List<Grades> TousLesGrades() {
        return grd.findAll();
    }

    @Override
    public Grades addGrades(Grades g) {
        grd.save(g);
        int id = g.getId();
        return grd.getById(id);
    }

    @Override
    public Grades ModifyGrades(Grades g) {
        grd.save(g);
        int id = g.getId();
        return grd.getById(id);
    }

    @Override
    public Grades supprimerGrades(Grades g) {
        grd.delete(g);
        return g;
    }

    @Override
    public Grades leGrade(int id) {
        return grd.getById(id);
    }
}
