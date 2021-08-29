package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.Recover;
import erecrutement.finances.gov.ma.MEF.Services.IAccountService;
import erecrutement.finances.gov.ma.MEF.Services.IEmailSending;
import erecrutement.finances.gov.ma.MEF.Services.IGestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestPasswordRecovery {

    private IEmailSending iEmailSending;
    private IAccountService iAccountService;
    private IGestionnaireService iGestionnaireService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setiGestionnaireService(IGestionnaireService iGestionnaireService) {
        this.iGestionnaireService = iGestionnaireService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setiAccountService(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @Autowired
    public void setiEmailSending(IEmailSending iEmailSending) {
        this.iEmailSending = iEmailSending;
    }

    @GetMapping(path = "RecoveryPass/{login}")
    public ResponseEntity<Object> Search(@PathVariable("login") String email) throws MessagingException {
        if (iAccountService.loadUserByLogin(email)==null){
            Map<String, String> map = new HashMap<>();
            map.put("error", "Utilisateur n'existe pas ou/et le compte n'est plus fonctionnel contactez l'administrateur pour plus d'informations");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }
        else
        {
            String codepin = iEmailSending.RecoverPassword(email);
            Map<String, String> map = new HashMap<>();
            map.put("pin", codepin);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping(path = "UpdateRecover/{code}")
    public ResponseEntity<Object> recovery(@RequestBody Recover recover,@PathVariable("code") String pin){

        Map<String,String> error = new HashMap<>();
        if (iAccountService.loadUserByLogin(recover.getLogin())==null){
            error.put("email","utilisateur n'existe pas");
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }else if(iAccountService.loadUserByLogin(recover.getLogin()).getRoles().contains("DENIED")){
            error.put("email","Compte n'est plus fonctionnel essayez de contacter l'administrateur");
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }else if (!recover.getPassword().equals(recover.getConfirmpassword())){
            error.put("password","mot de passe et sa confirmation sont incorrectes entrez le meme mot de passe");
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else if (recover.getPassword().equals("") && recover.getConfirmpassword().equals("")){
            error.put("password","mot de passe et sa confirmation sont incorrectes entrez le meme mot de passe");
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else if(!recover.getPin().equals(pin)){
            error.put("pin","Code pin n'est pas correcte");
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }else {
            Gestionnaires g = iAccountService.loadUserByLogin(recover.getLogin());
            g.setMotDePasse(bCryptPasswordEncoder.encode(recover.getPassword()));
            return new ResponseEntity<>(iGestionnaireService.ModifyGestionnaire(g,g.getIdGestionnaire()),HttpStatus.OK);
        }
    }
}
