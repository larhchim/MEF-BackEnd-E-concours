package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DirectionService implements InterfaceService<Directions>{

    private DirectionsDAO dir;

    @Autowired
    public void setDir(DirectionsDAO dir) {
        this.dir = dir;
    }

    @Override
    public List<Directions> TousLesObjets() {
        return dir.findAll();
    }

    @Override
    public Optional<Directions> addObjet(Directions g) {
        dir.save(g);
        return dir.findById(g.getId());
    }

    @Override
    public Directions ModifyObjet(Directions g, int aid) {
        g.setId(aid);
        return dir.save(g);
    }

    @Override
    public Optional<Directions> leComposant(int id) {
        return dir.findById(id);
    }

    @Override
    public Page<Directions> chercher(String mc, int page, int size) {

        return dir.search(mc,PageRequest.of(page, size));

    }

}
