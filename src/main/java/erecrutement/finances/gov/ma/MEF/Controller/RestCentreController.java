package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.CentresDAO;
import erecrutement.finances.gov.ma.MEF.Models.*;
import erecrutement.finances.gov.ma.MEF.Services.InterfaceService;
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
public class RestCentreController implements IController<Centres>,DirExtension{

    private InterfaceService<Centres> centre;

    private CentresDAO centresDAO;

    @Autowired
    public void setCentresDAO(CentresDAO centresDAO) {
        this.centresDAO = centresDAO;
    }

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

    @Override
    public ResponseEntity<Object> AddObjectExtension(@Valid Centres centres, BindingResult bindingResult) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateCentre/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateObjectExtension(@Valid Centres cnc, BindingResult bindingResult,@PathVariable("id") int id) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(centre.ModifyObjet(cnc, id), HttpStatus.OK);
    }

    @Override
    public Directions Search(String mc) {
        return null;
    }

    @Override
    @PostMapping(path = "addCentre",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> createOne(@Valid Centres centres, BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

       Centres c = centresDAO.findCentresByAdresseAndVilleAndSalle(centres.getAdresse(),centres.getVille(),centres.getSalle());

        if (c!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(centre.addObjet(centres),HttpStatus.OK);

    }
}
