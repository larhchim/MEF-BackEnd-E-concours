package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.HistoriqueGestionnaire;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.HistoryGestionnaireService;
import erecrutement.finances.gov.ma.MEF.Services.IGestionnaireService;
import erecrutement.finances.gov.ma.MEF.Services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestHistoryGestController implements IController<HistoriqueGestionnaire>,IHistoryCustom{

    private InterfaceService<HistoriqueGestionnaire> histGest;

    private GestionnaireDAO gest;

    @Autowired
    public void setGest(GestionnaireDAO gest) {
        this.gest = gest;
    }

    @Autowired
    public void setHistGest(HistoryGestionnaireService histGest) {
        this.histGest = histGest;
    }

    @Override
    @GetMapping(path="ListhistGests",produces= {"application/json"})
    public List<HistoriqueGestionnaire> ListeObjects() {
        return histGest.TousLesObjets();
    }

    @Override
    @PostMapping(path = "AddHistGest",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<HistoriqueGestionnaire> AddObject(HistoriqueGestionnaire r) {
        return histGest.addObjet(r);
    }


    @Override
    @GetMapping(path="HistGestionnaire/{id}",produces= {"application/json"})
    public Optional<HistoriqueGestionnaire> ObjectById(int id) {
        return histGest.leComposant(id);
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateHistGest/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<HistoriqueGestionnaire> ModifyObject(HistoriqueGestionnaire cnc, int id) {
        return new ResponseEntity<HistoriqueGestionnaire>(histGest.ModifyObjet(cnc, id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "AddHistGest/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<HistoriqueGestionnaire> AddObject(HistoriqueGestionnaire r, int id) {
        r.setGestionnaire(gest.getById(id));
        return histGest.addObjet(r);
    }
}
