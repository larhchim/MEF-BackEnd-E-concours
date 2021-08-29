package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.DirectionService;
import erecrutement.finances.gov.ma.MEF.Services.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class RestDirectionsController implements IController<Directions>,DirExtension{

    private InterfaceService<Directions> dirs;

    private DirectionsDAO di;

    @Autowired
    public void setDi(DirectionsDAO di) {
        this.di = di;
    }

    @Autowired
    public void setDirs(DirectionService dirs) {
        this.dirs = dirs;
    }

    @Override
    @GetMapping(path="ListDirs",produces= {"application/json"})
    public List<Directions> ListeObjects() {
        return dirs.TousLesObjets();
    }

    @Override
    public Optional<Directions> AddObject(Directions r) {
        return dirs.addObjet(r);
    }

    @Override
    @GetMapping(path="Directions/{id}",produces= {"application/json"})
    public Optional<Directions> ObjectById(int id) {
        return dirs.leComposant(id);
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Directions> ModifyObject(Directions cnc, int id) {
        return new ResponseEntity<Directions>(dirs.ModifyObjet(cnc, id),HttpStatus.OK);
    }

    @Override
    @GetMapping(path="SearchDirections",produces= {"application/json"})
    public Page<Directions> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return dirs.chercher("%"+mc+"%",page,size);
    }

    @Override
    @PostMapping(path = "AddDirs",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddObjectExtension(@Valid Directions directions, BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        Directions c = di.OneDir(directions.getNom());

        if (c!=null){
            errors.put("error","Direction existe deja");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(dirs.addObjet(directions),HttpStatus.OK);    }

    @Override
    @PutMapping(path="ModifyDir/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateObjectExtension(@Valid Directions directions, BindingResult bindingResult,@PathVariable("id") int id) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }


        return new ResponseEntity<>(dirs.ModifyObjet(directions, id),HttpStatus.OK);
    }


    @Override
    @GetMapping(path = "SearchOneDirection/{mc}")
    public Directions Search(@PathVariable("mc") String mc) {
        return di.OneDir(mc);
    }

    @Override
    public ResponseEntity<Object> createOne(@Valid Centres centres, BindingResult bindingResult) {
        return null;
    }
}
