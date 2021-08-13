package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.VilleDAO;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VilleService implements InterfaceService<Ville>{

    private VilleDAO vl;

    @Autowired
    public void setVl(VilleDAO vl) {
        this.vl = vl;
    }

    @Override
    public List<Ville> TousLesObjets() {
        return vl.findAll();
    }

    @Override
    public Optional<Ville> addObjet(Ville g) {
        vl.save(g);
        return vl.findById(g.getIdVille());
    }

    @Override
    public Ville ModifyObjet(Ville g, int aid) {
        g.setIdVille(aid);
        return vl.save(g);
    }

    @Override
    public Optional<Ville> leComposant(int id) {
        return vl.findById(id);
    }

    @Override
    public Page<Ville> chercher(String mc, int page, int size) {
        return vl.search(mc, PageRequest.of(page, size));
    }

}
