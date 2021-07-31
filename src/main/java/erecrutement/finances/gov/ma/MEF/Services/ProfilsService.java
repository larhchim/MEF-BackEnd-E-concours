package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.ProfilDAO;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilsService implements IProfilsServices{

    private ProfilDAO prf;

    @Autowired
    public void setPrf(ProfilDAO prf) {
        this.prf = prf;
    }

    @Override
    public List<Profils> TousLesProfils() {
        return prf.findAll();
    }

    @Override
    public Profils addProfils(Profils g) {
        prf.save(g);
        int id = g.getIdProfil();
        return prf.getById(id);
    }

    @Override
    public Profils ModifyProfils(Profils g) {
        prf.save(g);
        int id = g.getIdProfil();
        return prf.getById(id);
    }

    @Override
    public Profils supprimerProfils(Profils g) {
        prf.delete(g);
        return g;
    }

    @Override
    public Profils leProfil(int id) {
        return prf.getById(id);
    }
}
