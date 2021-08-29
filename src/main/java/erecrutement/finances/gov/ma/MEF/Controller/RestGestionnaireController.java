package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.IAccountService;
import erecrutement.finances.gov.ma.MEF.Services.IGestionnaireService;
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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestGestionnaireController implements IController<Gestionnaires>{

    private IGestionnaireService gest;
    private IAccountService iAccountService;

    @Autowired
    public void setiAccountService(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

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
    public ResponseEntity<Gestionnaires> ModifyObject(Gestionnaires cnc, int id) {

        return new ResponseEntity<Gestionnaires>(gest.ModifyGestionnaire(cnc,id), HttpStatus.OK);

    }

    @Override
    @GetMapping(path="SearchGestionnaires",produces= {"application/json"})
    public Page<Gestionnaires> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return gest.chercher("%"+mc+"%",page,size);
    }

    @Override
    @PostMapping(path = "AddGest",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddObjectExtension(@Valid Gestionnaires gestionnaires, BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        Gestionnaires c = iAccountService.loadUserByLogin(gestionnaires.getLogin());


        if (c!=null){
            errors.put("error","User Already Exists");
            return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(gest.addGestionnaire(gestionnaires),HttpStatus.OK);
    }

    @Override
    @PutMapping(path="UpdateGest/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateObjectExtension(@Valid Gestionnaires gestionnaires, BindingResult bindingResult,@PathVariable("id") int id) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(gest.ModifyGestionnaire(gestionnaires,id),HttpStatus.OK);
    }

}
