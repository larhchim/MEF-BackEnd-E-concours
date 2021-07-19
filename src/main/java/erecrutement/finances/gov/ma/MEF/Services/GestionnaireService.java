package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionnaireService implements IGestionnaireService{

    @Autowired
    GestionnaireDAO gt;

    @Override
    public List<Gestionnaires> TousLesGestionnaires() {
        return gt.findAll();
    }

    @Override
    public Optional<Gestionnaires> addGestionnaire(Gestionnaires g) {
        gt.save(g);
        int id = g.getIdGestionnaire();
        return gt.findById(id);
    }

    @Override
    public Gestionnaires ModifyGestionnaire(Gestionnaires g) {
        gt.save(g);
        int id = g.getIdGestionnaire();
        return gt.getById(id);
    }

    @Override
    public Gestionnaires supprimerGestionnaire(Gestionnaires g) {
        gt.delete(g);
        return g;
    }

    @Override
    public Gestionnaires leGestionnaire(int id) {
        return gt.getById(id);
    }
}
