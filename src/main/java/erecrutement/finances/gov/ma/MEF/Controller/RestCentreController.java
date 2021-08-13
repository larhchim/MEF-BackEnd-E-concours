package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
import erecrutement.finances.gov.ma.MEF.Services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestCentreController implements IController<Centres>{

    private InterfaceService<Centres> centre;

    @Autowired
    public void setCentre(InterfaceService<Centres> centre) {
        this.centre = centre;
    }

    @Override
    @GetMapping(path="listCentres",produces= {"application/json"})
    public List<Centres> ListeObjects() {
        return centre.TousLesObjets();
    }

    @Override
    @PostMapping(path = "addCentre",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<Centres> AddObject(Centres r) {
        return centre.addObjet(r);
    }

    @Override
    @GetMapping(path="Centre/{id}",produces= {"application/json"})
    public Optional<Centres> ObjectById(int id) {
        return centre.leComposant(id);
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateCentre/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Centres> ModifyObject(Centres cnc, int id) {
        return new ResponseEntity<Centres>(centre.ModifyObjet(cnc, id), HttpStatus.OK);
    }

    @Override
    @GetMapping(path="SearchCentres",produces= {"application/json"})
    public Page<Centres> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return centre.chercher("%"+mc+"%",page,size);
    }
}
