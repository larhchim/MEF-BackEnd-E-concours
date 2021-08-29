package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.ProfilDAO;
import erecrutement.finances.gov.ma.MEF.Models.Grades;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
import erecrutement.finances.gov.ma.MEF.Services.IProfilsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestProfileController implements IController <Profils>{

    private IProfilsServices prfl;
    private ProfilDAO profilDAO;

    @Autowired
    public void setProfilDAO(ProfilDAO profilDAO) {
        this.profilDAO = profilDAO;
    }

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

    @Override
    @PostMapping(path = "addProfile",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddObjectExtension(@Valid Profils profils, BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }

        Profils c = profilDAO.lookforProfil(profils.getIntitled(),profils.getType());

        if (c!=null){
            errors.put("error","Profil Already Exists");
            return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(prfl.addProfils(profils),HttpStatus.OK);
    }

    @Override
    @PutMapping(path="UpdateProfile/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateObjectExtension(@Valid Profils profils, BindingResult bindingResult, @PathVariable("id") int id) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }

        profils.setIdProfil(id);

        return new ResponseEntity<>(prfl.ModifyProfils(profils),HttpStatus.OK);
    }
}
