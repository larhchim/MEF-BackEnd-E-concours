package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
import erecrutement.finances.gov.ma.MEF.Services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestVilleController implements IController<Ville>{


    private InterfaceService<Ville> ville;

    @Autowired
    public void setVille(InterfaceService<Ville> ville) {
        this.ville = ville;
    }

    @Override
    @GetMapping(path="ListVilles",produces= {"application/json"})
    public List<Ville> ListeObjects() {
        return ville.TousLesObjets();
    }

    @Override
    @PostMapping(path = "addVille",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<Ville> AddObject(Ville r) {
        return ville.addObjet(r);
    }

    @Override
    @GetMapping(path="ville/{id}",produces= {"application/json"})
    public Optional<Ville> ObjectById(int id) {
        return ville.leComposant(id);
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateVille/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Ville> ModifyObject(Ville cnc, int id) {
        return new ResponseEntity<Ville>(ville.ModifyObjet(cnc, id), HttpStatus.OK);
    }


}
