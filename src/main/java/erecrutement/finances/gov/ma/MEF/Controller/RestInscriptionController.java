package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import erecrutement.finances.gov.ma.MEF.Services.ICandidatureService;
import erecrutement.finances.gov.ma.MEF.Services.IMd5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestInscriptionController {

    private ICandidatureService candidatureService;
    private InscriptionsDAO inscriptionsDAO;

    @Autowired
    public void setInscriptionsDAO(InscriptionsDAO inscriptionsDAO) {
        this.inscriptionsDAO = inscriptionsDAO;
    }

    @Autowired
    public void setCandidatureService(ICandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }



    @PostMapping(path = "AddInscription/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddInscription(@Valid @RequestBody Inscriptions inscriptions, BindingResult bindingResult, @PathVariable("id") int id){
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            System.out.println(errors);

            if (inscriptions.getRemotDePasse()==null || inscriptions.getRemotDePasse() == null){
                errors.put("motDePasse", "*Veuillez confirmer le meme mot de passe*");
                errors.put("remotDePasse", "*Veuillez confirmer le meme mot de passe*");
            }

            if (inscriptions.getRemotDePasse()!=null && inscriptions.getMotDePasse()!=null){
                if (!inscriptions.getRemotDePasse().equals(inscriptions.getMotDePasse())) {
                    errors.put("motDePasse", "*Veuillez confirmer le meme mot de passe*");
                    errors.put("remotDePasse", "*Veuillez confirmer le meme mot de passe*");
                }
            }

            inscriptions.getFichiers().forEach(r->{
                if (!r.getFichier().toLowerCase().contains(".pdf")){
                    errors.put("fichiersJoints2","*Veuillez seulement importer des fichiers pdf*");
                }
            });

            int val = 3 -(inscriptions.getFichiers().size());
            if (inscriptions.getFichiers().size()<2){
                errors.put("fichiersJoints1","*Veuillez rensegner les "+val+" autres fichiers Sous format PDF");
            }




            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }



        Inscriptions c = inscriptionsDAO.Verification(inscriptions.getCin(),id,inscriptions.getAdresseEmail());

        if (c!=null){
            errors.put("error","Inscription existe deja pour ce numero CIN dans le concours Numero: "+id);
            inscriptions.getFichiers().forEach(r->{
                if (!r.getFichier().toLowerCase().contains(".pdf")){
                    errors.put("fichiersJoints2","*Veuillez seulement importer des fichiers pdf*");
                }
            });


            int val = 3 -(inscriptions.getFichiers().size());
            if (inscriptions.getFichiers().size()<2){
                errors.put("fichiersJoints1","*Veuillez rensegner les "+val+" autres fichiers Sous format PDF");
            }
            return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
        }

        if (inscriptions.getRemotDePasse()!=null && inscriptions.getMotDePasse()!=null) {
            if (!inscriptions.getRemotDePasse().equals(inscriptions.getMotDePasse())) {
                errors.put("motDePasse", "*Veuillez confirmer le meme mot de passe*");
                errors.put("remotDePasse", "*Veuillez confirmer le meme mot de passe*");
                inscriptions.getFichiers().forEach(r->{
                    if (!r.getFichier().toLowerCase().contains(".pdf")){
                        errors.put("fichiersJoints2","*Veuillez seulement importer des fichiers pdf*");
                    }
                });


                int val = 3 -(inscriptions.getFichiers().size());
                if (inscriptions.getFichiers().size()<2){
                    errors.put("fichiersJoints1","*Veuillez rensegner les "+val+" autres fichiers Sous format PDF");
                }
                return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
            }
        }

        inscriptions.getFichiers().forEach(r->{
            if (!r.getFichier().toLowerCase().contains(".pdf")){
                errors.put("fichiersJoints2","*Veuillez seulement importer des fichiers pdf*");
            }
        });


        int val = 3 -(inscriptions.getFichiers().size());
        if (inscriptions.getFichiers().size()<2){
            errors.put("fichiersJoints1","*Veuillez rensegner les "+val+" autres fichiers Sous format PDF");
        }

        if (errors.size()>0){
            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(candidatureService.AddInscription(inscriptions, id),HttpStatus.OK);
    }

    @GetMapping(path = "AllSubscriptions",produces = {"application/json;charset=UTF-8"})
    public List<Inscriptions> GetAllInsc(){
        return candidatureService.GetAllInscriptions();
    }

    @GetMapping(path="SearchInscriptions/{id}",produces= {"application/json"})
    public Page<Inscriptions> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size,
            @PathVariable("id") int id
    ) {
        return inscriptionsDAO.SearchInscriptions("%"+mc+"%", PageRequest.of(page, size),id);
    }



    @GetMapping (path = "UpdateInscription/{idi}/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateInscription(@PathVariable("idi") int id,@PathVariable("id") int level){
        Inscriptions inscriptions =inscriptionsDAO.getById(id);

        if (level==1){
            inscriptions.setEtatCandidature(true);
            return new ResponseEntity<>(inscriptionsDAO.save(inscriptions),HttpStatus.OK);
        }else if (level ==2){
            inscriptions.setEtatCandidature(false);
            return new ResponseEntity<>(inscriptionsDAO.save(inscriptions),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
