package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.IGestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestGestionnaireController implements IController<Gestionnaires>{

    private IGestionnaireService gest;

    @Autowired
    public void setGest(IGestionnaireService gest) {
        this.gest = gest;
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="ListGests",produces= {"application/json"})
    public List<Gestionnaires> ListeObjects() {
        return gest.TousLesGestionnaires();
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "AddGest",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<Gestionnaires> AddObject(Gestionnaires r) {
        return gest.addGestionnaire(r);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="Gestionnaire/{id}",produces= {"application/json"})
    public Optional<Gestionnaires> ObjectById(int id) {
        return gest.leGestionnaire(id);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("deleteGest/{id}")
    public ResponseBean DeleteObject(int id) {
        return gest.supprimerGestionnaire(id);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path="UpdateGest/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Gestionnaires> ModifyObject(Gestionnaires cnc, int id) {

        return new ResponseEntity<Gestionnaires>(gest.ModifyGestionnaire(cnc,id), HttpStatus.OK);

    }

}
