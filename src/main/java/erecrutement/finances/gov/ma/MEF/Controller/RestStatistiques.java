package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.*;
import erecrutement.finances.gov.ma.MEF.Models.*;
import erecrutement.finances.gov.ma.MEF.Services.GestionnaireService;
import erecrutement.finances.gov.ma.MEF.Services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestStatistiques {

    private GestionnaireDAO gestionnaireDAO;
    private ConcoursDAO concoursDAO;
    private InscriptionsDAO inscriptionsDAO;
    private CentresDAO centresDAO;
    private DirectionsDAO directionsDAO;
    private ProfilDAO profilDAO;
    private HistoryStatusCandidateDAO historyStatusCandidateDAO;


    @Autowired
    public void setHistoryStatusCandidateDAO(HistoryStatusCandidateDAO historyStatusCandidateDAO) {
        this.historyStatusCandidateDAO = historyStatusCandidateDAO;
    }

    @Autowired
    public void setProfilDAO(ProfilDAO profilDAO) {
        this.profilDAO = profilDAO;
    }


    @Autowired
    public void setDirectionsDAO(DirectionsDAO directionsDAO) {
        this.directionsDAO = directionsDAO;
    }

    @Autowired
    public void setInscriptionsDAO(InscriptionsDAO inscriptionsDAO) {
        this.inscriptionsDAO = inscriptionsDAO;
    }

    @Autowired
    public void setConcoursDAO(ConcoursDAO concoursDAO) {
        this.concoursDAO = concoursDAO;
    }

    @Autowired
    public void setCentresDAO(CentresDAO centresDAO) {
        this.centresDAO = centresDAO;
    }

    @Autowired
    public void setGestionnaireDAO(GestionnaireDAO gestionnaireDAO) {
        this.gestionnaireDAO = gestionnaireDAO;
    }

    @GetMapping(path = "GestGlobalStats")
    public ResponseEntity<Object> GestionnairesStats(){

        Map<String,Integer> map = new HashMap<>();

        map.put("Desactivated", gestionnaireDAO.stats0());
        map.put("Gestionnaires", gestionnaireDAO.Stats1());
        map.put("Admins", gestionnaireDAO.Stats2());
        map.put("GestionnairesLV1", gestionnaireDAO.Stats3());
        map.put("GestionnairesLV2", gestionnaireDAO.Stats4());

        return new ResponseEntity<>(map,HttpStatus.OK);

    }

    @GetMapping(path = "GestDirectionStats/{id}")
    public ResponseEntity<Object> GestionnairesStatsDirections(@PathVariable("id") int id){

        Map<String,Integer> map = new HashMap<>();
        Directions directions = directionsDAO.getById(id);

        map.put("Gestionnaires", gestionnaireDAO.stats5(directions));
        map.put("Admins", gestionnaireDAO.stats6(directions));
        map.put("GestionnairesLV1", gestionnaireDAO.stats7(directions));
        map.put("GestionnairesLV2", gestionnaireDAO.stats8(directions));
        map.put("Desactivated", gestionnaireDAO.stats9(directions));

        return new ResponseEntity<>(map,HttpStatus.OK);

    }

/***/
    @GetMapping(path = "ConcoursStatsDirection/{Did}")
    public ResponseEntity<Object> ConcoursOfDirection(@PathVariable("Did") int DirectionId){

        Map<String,Integer> map = new HashMap<>();
        Directions directions = directionsDAO.getById(DirectionId);

        map.put("Tous Les Concours",concoursDAO.stats0());
        map.put("Concours de la direction",concoursDAO.stats1(directions));
        map.put("Actifs",concoursDAO.stats2(directions));
        map.put("Términé",concoursDAO.stats3(directions));

        return new ResponseEntity<>(map,HttpStatus.OK);

    }

    @GetMapping(path = "ConcoursOfProfileStats/{Pid}")
    public ResponseEntity<Object> ConcoursOfProfile(@PathVariable("Pid") int ProfileId){

        Map<String,Integer> map = new HashMap<>();
        Profils profils = profilDAO.getById(ProfileId);

        map.put("Concours Actifs",concoursDAO.stats4());
        map.put("Actifs du Profil",concoursDAO.stats5(profils));
        map.put("Términé du Profil",concoursDAO.stats6(profils));

        return new ResponseEntity<>(map,HttpStatus.OK);

    }

    @GetMapping(path = "InscriptionsStats")
    public ResponseEntity<Object> InscriptionsStats(){

        Map<String,Integer> map = new HashMap<>();
        map.put("Les Inscriptions", inscriptionsDAO.stats1());
        map.put("Inscription Accepté", inscriptionsDAO.stats3());
        map.put("Inscription Refusé", inscriptionsDAO.stats4());

        return new ResponseEntity<>(map,HttpStatus.OK);

    }


    @GetMapping(path = "InscriptionsStatsConcours/{id}")
    public ResponseEntity<Object> InscriptionsOfConcours(@PathVariable("id") int id){

        Map<String,Integer> map = new HashMap<>();
        Concours concours = concoursDAO.getById(id);
        map.put("Les Inscriptions", inscriptionsDAO.stats1());
        map.put("Inscriptions Du Concours", inscriptionsDAO.stats2(concours));
        map.put("Inscriptions Du Concours Accepté", inscriptionsDAO.stats5(concours));
        map.put("Inscriptions Du Concours Refusé", inscriptionsDAO.stats6(concours));

        return new ResponseEntity<>(map,HttpStatus.OK);

    }

    @GetMapping(path = "CentreStats/{id}")
    public ResponseEntity<Object> CentresStats(@PathVariable("id") int id){

        Map<String,Integer> map = new HashMap<>();
        Concours concours = concoursDAO.getById(id);
        map.put("Tous les Centres",centresDAO.stats2());
        map.put("Centres du concours",centresDAO.stats1(concours));

        return new ResponseEntity<>(map,HttpStatus.OK);

    }


    @GetMapping(path = "InscriptionsStatsConcours/TrancheAge")
    public ResponseEntity<Object> InscriptionsOfConcoursTrancheAge(){

        Map<String,Integer> map = new HashMap<>();
        map.put("TrancheUnder20", inscriptionsDAO.TrancheUnder20());
        map.put("TrancheBetween20And30",inscriptionsDAO.TrancheBetween20And30());
        map.put("TrancheBetween30And40",inscriptionsDAO.TrancheBetween30And40());
        map.put("TrancheBetween40And50",inscriptionsDAO.TrancheBetween40And50());
        map.put("TrancheBetween50And60",inscriptionsDAO.TrancheBetween50And60());
        map.put("TrancheMoreThan60",inscriptionsDAO.TrancheMoreThan60());
        return new ResponseEntity<>(map,HttpStatus.OK);

    }


    @GetMapping(path = "InscriptionsStatsConcours/TrancheAgeByConcours/{id}")
    public ResponseEntity<Object> InscriptionsOfConcoursTrancheAge(@PathVariable("id") int id){

        Map<String,Integer> map = new HashMap<>();
        map.put("TrancheUnder20", inscriptionsDAO.TrancheUnder20ByConcours(id));
        map.put("TrancheBetween20And30",inscriptionsDAO.TrancheBetween20And30ByConcours(id));
        map.put("TrancheBetween30And40",inscriptionsDAO.TrancheBetween30And40ByConcours(id));
        map.put("TrancheBetween40And50",inscriptionsDAO.TrancheBetween40And50ByConcours(id));
        map.put("TrancheBetween50And60",inscriptionsDAO.TrancheBetween50And60ByConcours(id));
        map.put("TrancheMoreThan60",inscriptionsDAO.TrancheMoreThan60ByConcours(id));
        return new ResponseEntity<>(map,HttpStatus.OK);

    }


    @GetMapping(path = "HistoryChangeStatusOfCandidates")
    public ResponseEntity<Object> HistoryChangeStatusOfCandidates(){

        Map<String,Integer> map = new HashMap<>();
        map.put("Acceptance", historyStatusCandidateDAO.Acceptance());
        map.put("Refused", historyStatusCandidateDAO.Refused());
        map.put("Instance", historyStatusCandidateDAO.Instance());
        return new ResponseEntity<>(map,HttpStatus.OK);

    }


    @GetMapping(path = "HistoryChangeStatusOfCandidates/Username/{id}")
    public ResponseEntity<Object> HistoryChangeStatusOfCandidates(@PathVariable("id") int id){

        Map<String,Integer> map = new HashMap<>();
        Gestionnaires gestionnaires = gestionnaireDAO.getById(id);
        String login = gestionnaires.getLogin();
        map.put("Acceptance", historyStatusCandidateDAO.AcceptanceByUsername(login));
        map.put("Refused", historyStatusCandidateDAO.RefusedByUsername(login));
        map.put("Instance", historyStatusCandidateDAO.InstanceByUsername(login));
        return new ResponseEntity<>(map,HttpStatus.OK);

    }




}
