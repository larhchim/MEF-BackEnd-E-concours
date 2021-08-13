package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestConcoursController {

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
    public Concours AjouterConcours(@RequestBody Concours r) {

        return cncs.addConcours(r);

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
    public ResponseEntity<Concours> ModifyConcours(@RequestBody Concours cnc, @PathVariable int aid){

        return new ResponseEntity<Concours>(cncs.ModifyConcours(cnc,aid), HttpStatus.OK);

    }

    @GetMapping(path="SearchConcours",produces= {"application/json"})
    public Page<Concours> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return cncs.chercher("%"+mc+"%",page,size);
    }
















}

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/**").permitAll();
        http.csrf().disable();

    }
}
