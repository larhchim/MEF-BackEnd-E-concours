package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import erecrutement.finances.gov.ma.MEF.Services.ICandidatureService;
import erecrutement.finances.gov.ma.MEF.Services.IMd5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestInscriptionController {

    private ICandidatureService candidatureService;

    @Autowired
    public void setCandidatureService(ICandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }


    @PostMapping(path = "AddInscription/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Inscriptions AddInscription(@RequestBody Inscriptions inscriptions, @PathVariable("id") int id){
      return candidatureService.AddInscription(inscriptions, id);
    }

    @GetMapping(path = "AllSubscriptions",produces = {"application/json;charset=UTF-8"})
    public List<Inscriptions> GetAllInsc(){
        return candidatureService.GetAllInscriptions();
    }
}
