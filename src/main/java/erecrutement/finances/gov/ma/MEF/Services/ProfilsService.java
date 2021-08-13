package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.ProfilDAO;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Profils> addProfils(Profils g) {
        prf.save(g);
        int id = g.getIdProfil();
        return prf.findById(id);
    }

    @Override
    public ResponseEntity<Profils> ModifyProfils(Profils g) {
        prf.save(g);
        int id = g.getIdProfil();
        return new ResponseEntity<Profils>(prf.getById(id), HttpStatus.OK);
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

    @Override
    public Page<Profils> chercher(String mc, int size, int page) {
        return prf.chercher(mc, PageRequest.of(page,size));
    }
}
