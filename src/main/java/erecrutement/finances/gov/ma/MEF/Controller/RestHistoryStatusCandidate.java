package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.HistoryStatusCandidateDAO;
import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.HistoriqueChangementStatus;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestHistoryStatusCandidate {

    private HistoryStatusCandidateDAO historyStatusCandidateDAO;

    private InscriptionsDAO inscriptionsDAO;

    @Autowired
    public void setInscriptionsDAO(InscriptionsDAO inscriptionsDAO) {
        this.inscriptionsDAO = inscriptionsDAO;
    }

    @Autowired
    public void setHistoryStatusCandidateDAO(HistoryStatusCandidateDAO historyStatusCandidateDAO) {
        this.historyStatusCandidateDAO = historyStatusCandidateDAO;
    }

    @PostMapping(path = "AddHistoryCandidate/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddObjectExtension(@RequestBody HistoriqueChangementStatus historiqueChangementStatus,@PathVariable("id") int id) {
        historiqueChangementStatus.setDateAction(new Date());
        historiqueChangementStatus.setInscription(inscriptionsDAO.getById(id));
        if (historiqueChangementStatus.getDescription().contains("Accepté")){
            Inscriptions inscriptions = inscriptionsDAO.getById(id);
            inscriptions.setEtatCandidature(true);
            inscriptionsDAO.save(inscriptions);
            return new ResponseEntity<>(historyStatusCandidateDAO.save(historiqueChangementStatus), HttpStatus.OK);
        }else if (historiqueChangementStatus.getDescription().contains("Refusé")){
            Inscriptions inscriptions = inscriptionsDAO.getById(id);
            inscriptions.setEtatCandidature(false);
            inscriptionsDAO.save(inscriptions);
            return new ResponseEntity<>(historyStatusCandidateDAO.save(historiqueChangementStatus), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(historyStatusCandidateDAO.save(historiqueChangementStatus), HttpStatus.OK);
        }
    }

    @GetMapping(path="SearchHistoryCandidate/{id}",produces= {"application/json"})
    public Page<HistoriqueChangementStatus> chercher(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size,
            @PathVariable("id") int id
    ) {
        return historyStatusCandidateDAO.search(PageRequest.of(page, size),id);
    }


}
