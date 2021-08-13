package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.GradeDAO;
import erecrutement.finances.gov.ma.MEF.DAO.ProfilDAO;
import erecrutement.finances.gov.ma.MEF.Models.Grades;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradesService implements IGradesService{

    private GradeDAO grd;

    private ProfilDAO prfl;

    @Autowired
    public void setPrfl(ProfilDAO prfl) {
        this.prfl = prfl;
    }

    @Autowired
    public void setGrd(GradeDAO grd) {
        this.grd = grd;
    }

    @Override
    public List<Grades> TousLesGrades() {
        return grd.findAll();
    }

    @Override
    public Optional<Grades> addGrades(Grades g) {
        grd.save(g);
        int id = g.getId();
        return grd.findById(id);
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

    @Override
    public Page<Grades> chercher(String mc, int page, int size) {
        return grd.search(mc, PageRequest.of(page, size));
    }

    @Override
    public List<Profils> ProfilsManqueGrade(int id) {
        List<Profils> TousLesProfils = prfl.findAll();
        List<Profils> PrfilsDuGrade = leGrade(id).getProfils();

        //return Profils qui ne sont pas encore dans le grade

        List<Profils> ProfilsRetour = new ArrayList<>();

        for (Profils p:TousLesProfils) {

            if(!PrfilsDuGrade.contains(p)){
                ProfilsRetour.add(p);
            }

        }
        return ProfilsRetour;
    }
}
