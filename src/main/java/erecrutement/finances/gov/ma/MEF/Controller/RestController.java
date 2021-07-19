package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    ConcoursService cncs;

    @Autowired
    IGestionnaireService gests;

    @Autowired
    IGradesService grds;

    @Autowired
    IProfilsServices prfls;

    @GetMapping(path="listcnc",produces= {"application/json"})
    public List<Concours> home() {

        System.out.println(cncs.TousLesConcours());

       return cncs.TousLesConcours();

    }


    @GetMapping(path="add",produces= {"application/json"})
    public RedirectView  add() {

    Concours r = new Concours();
    r.setDateConcours(new Date());
    r.setDateLimiteConcours(new Date());
    r.setEtat(true);
    r.setIntitled("Concours nouveau");
    r.setNombrePostes(4555);
    cncs.addConcours(r);
        RedirectView rv = new RedirectView("listcnc");
        return rv ;

    }

    @GetMapping("cncs/{aid}")
    public Concours conc(@PathVariable int aid) {

        return cncs.leConcours(aid);

    }







}
