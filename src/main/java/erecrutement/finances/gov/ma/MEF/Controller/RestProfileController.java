package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Profils;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
import erecrutement.finances.gov.ma.MEF.Services.IProfilsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestProfileController implements IController <Profils>{

    private IProfilsServices prfl;

    @Autowired
    public void setPrfl(IProfilsServices prfl) {
        this.prfl = prfl;
    }

    @Override
    @GetMapping(path = "AllProfiles",produces = {"application/json;charset=UTF-8"})
    public List<Profils> ListeObjects() {
        return prfl.TousLesProfils();
    }

    @Override
    @PostMapping(path = "addProfile",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<Profils> AddObject(Profils r) {
        return prfl.addProfils(r);
    }

    @Override
    public Optional<Profils> ObjectById(int id) {
        return Optional.empty();
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateProfile/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Profils> ModifyObject(Profils cnc, int id) {
        cnc.setIdProfil(id);
        return prfl.ModifyProfils(cnc);
    }

    @Override
    @GetMapping(path="SearchProfils",produces= {"application/json"})
    public Page<Profils> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return prfl.chercher("%"+mc+"%",size,page);
    }
}
