package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.HistoriqueGestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.HistoriqueGestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoryGestionnaireService implements InterfaceService<HistoriqueGestionnaire>{

    private HistoriqueGestionnaireDAO his;

    @Autowired
    public void setHis(HistoriqueGestionnaireDAO his) {
        this.his = his;
    }

    @Override
    public List<HistoriqueGestionnaire> TousLesObjets() {
        return his.findAll();
    }

    @Override
    public Optional<HistoriqueGestionnaire> addObjet(HistoriqueGestionnaire g) {
         his.save(g);
         return his.findById(g.getIdHistorique());
    }

    @Override
    public HistoriqueGestionnaire ModifyObjet(HistoriqueGestionnaire g, int aid) {
        g.setIdHistorique(aid);
        return his.save(g);
    }

    @Override
    public Optional<HistoriqueGestionnaire> leComposant(int id) {
        return his.findById(id);
    }

    @Override
    public Page<HistoriqueGestionnaire> chercher(String mc, int page, int size) {
        return null;
    }
}
