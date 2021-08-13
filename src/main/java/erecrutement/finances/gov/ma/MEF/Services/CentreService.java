package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.CentresDAO;
import erecrutement.finances.gov.ma.MEF.Models.Centres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CentreService implements InterfaceService<Centres>{

    private CentresDAO cen;


    @Autowired
    public void setCen(CentresDAO cen) {
        this.cen = cen;
    }

    @Override
    public List<Centres> TousLesObjets() {
        return cen.findAll();
    }

    @Override
    public Optional<Centres> addObjet(Centres g) {
         cen.save(g);
         return cen.findById(g.getIdCentre());
    }

    @Override
    public Centres ModifyObjet(Centres g, int aid) {
        g.setIdCentre(aid);
        return cen.save(g);
    }

    @Override
    public Optional<Centres> leComposant(int id) {
        return cen.findById(id);
    }

    @Override
    public Page<Centres> chercher(String mc, int page, int size) {
        return cen.search(mc, PageRequest.of(page, size));
    }
}
