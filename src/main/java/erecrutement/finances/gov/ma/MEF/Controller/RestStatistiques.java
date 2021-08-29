package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.*;
import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
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


}
