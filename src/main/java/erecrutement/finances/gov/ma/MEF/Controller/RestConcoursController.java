package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.*;
import erecrutement.finances.gov.ma.MEF.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestConcoursController {
    int i = 0;

    private IConcoursService cncs;

    private IGestionnaireService gests;

    private IGradesService grds;

    private IProfilsServices prfls;

    @Autowired
    public void setCncs(IConcoursService cncs) {
        this.cncs = cncs;
    }

    @Autowired
    public void setGests(IGestionnaireService gests) {
        this.gests = gests;
    }

    @Autowired
    public void setGrds(IGradesService grds) {
        this.grds = grds;
    }

    @Autowired
    public void setPrfls(IProfilsServices prfls) {
        this.prfls = prfls;
    }



    @GetMapping(path="listcnc",produces= {"application/json"})
    public List<Concours> ListeConcours() {

       return cncs.TousLesConcours();

    }


    @PostMapping(path = "addConcours",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AjouterConcours(@Valid @RequestBody Concours r, BindingResult bindingResult) {

        //return cncs.addConcours(r);

        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }



        return new ResponseEntity<>(cncs.addConcours(r),HttpStatus.OK);

    }

    @GetMapping(path="cncs/{aid}",produces= {"application/json"})
    public Optional<Concours> ConcoursParId(@PathVariable int aid) {

        return cncs.leConcours(aid);

    }

    @DeleteMapping("deleteConc/{aid}")
    public ResponseBean supprimercnc(@PathVariable int aid){

        return cncs.supprimerConcours(aid);

    }

    @PutMapping(path="updateConc/{aid}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> ModifyConcours(@Valid @RequestBody  Concours cnc, BindingResult bindingResult,@PathVariable int aid){

        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(cncs.ModifyConcours(cnc,aid), HttpStatus.OK);

    }

    @GetMapping(path="SearchConcours",produces= {"application/json"})
    public Page<Concours> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return cncs.chercher("%"+mc+"%",page,size);
    }




    @GetMapping(path = "SearchBy/{intitledprof}",produces= {"application/json"})
    public Page<Concours> Filter2(@RequestParam(name = "intitledprof",defaultValue = "") String intitledprof, @RequestParam(name = "page",defaultValue = "0")int page,@RequestParam(name = "size",defaultValue = "5")int size){
        return cncs.Filter2("%"+intitledprof+"%",page,size);
    }

    @GetMapping(path = "SearchBYProf/{Profile}")
    public List<Recherche> fil(@PathVariable("Profile") String pro){
        Collection<Concours> col = new ArrayList<>();
        List<Recherche> colRes = new ArrayList<>();

        col = cncs.TousLesConcours();
        for (Concours c: col) {
            if(c.getEtat() == true) {
                for (Profils p : c.getProfils()) {
                    if (p.getIntitled().equalsIgnoreCase(pro)) {
                        colRes.add(new Recherche(c.getIdConcours(),c.getDateLimiteConcours(), c.getDatePassage(), c.getIntitled(), c.getNombrePostes(), c.getExigences()));
                        break;
                    }
                }
            }
        }
        return colRes;
    }



}

