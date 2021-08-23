package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.AppRoleRepository;
import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GestionnaireService implements IGestionnaireService{

    private GestionnaireDAO gt;

    private DirectionsDAO dir;

    private IEmailSending email;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private IAccountService iAccountService;

    private AppRoleRepository appRoleRepository;

    @Autowired
    public void setAppRoleRepository(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Autowired
    public void setiAccountService(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setGt(GestionnaireDAO gt) {
        this.gt = gt;
    }

    @Autowired
    public void setDir(DirectionsDAO dir) {
        this.dir = dir;
    }

    @Autowired
    public void setEmail(IEmailSending email) {
        this.email = email;
    }


    ResponseBean resp = new ResponseBean();

    @Override
    public List<Gestionnaires> TousLesGestionnaires() {
        return gt.findAll();
    }

    @Override
    public Optional<Gestionnaires> addGestionnaire(Gestionnaires g) {


        String mot = EmailSendingImlp.alphaNumericString(10);
        String pass = bCryptPasswordEncoder.encode(mot);

        g.setMotDePasse(pass);


        /*email.sendEmail(g.getLogin(),"<b>E-concours platform Authentification Credentials</b>",
                "" +"<b>These are your credentials for authentification</b> <br>"+
                        "<b><font color=red>Login: </font>"+g.getLogin()+"</b><br>" +
                        "<b><font color=red>Password: </font>"+mot+"</b>");*/
        try {
            email.Pin(g.getLogin(),mot);
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

        g.setMotDePasse(pass);
        gt.save(g);

        /*@
         Administrateur ADMIN
         */
        if (g.getAdministrator() == true){

            iAccountService.AddRoleToGestionnaire(g.getLogin(),"ADMIN");
            /*
            @Acces globale GESTLV1 et Acces locale GESTLV2
             */
        }else if (g.getHabilitation() == 2 && g.getHabilitation() ==1){

            iAccountService.AddRoleToGestionnaire(g.getLogin(),"GESTLV1");
            iAccountService.AddRoleToGestionnaire(g.getLogin(),"GESTLV2");

             /*
            @Acces Globale GESTLV1
             */

        }else if (g.getHabilitation() == 2){

            iAccountService.AddRoleToGestionnaire(g.getLogin(),"GESTLV1");

             /*
            @Acces locale GESTLV2
             */
        }else if (g.getHabilitation() == 1){

            iAccountService.AddRoleToGestionnaire(g.getLogin(),"GESTLV2");

            /*
            sans Acces == DENIED
             */
        }else{
            iAccountService.AddRoleToGestionnaire(g.getLogin(),"DENIED");

        }

        int id = g.getIdGestionnaire();
        return gt.findById(id);
    }

    @Override
    public Gestionnaires ModifyGestionnaire(Gestionnaires g,int id) {


        g.setIdGestionnaire(id);

        if (g.getAdministrator() == true){

            AppRole appRole = appRoleRepository.findByRoleName("ADMIN");
            List<AppRole> roles = new ArrayList<>();
            roles.add(appRole);
            g.setRoles(roles);
            /*
            @Acces globale GESTLV1 et Acces locale GESTLV2
             */
        }else if (g.getHabilitation() == 2 && g.getHabilitation() ==1){

            List<AppRole> roles = new ArrayList<>();
            AppRole appRole1 = appRoleRepository.findByRoleName("GESTLV1");
            AppRole appRole2 = appRoleRepository.findByRoleName("GESTLV2");
            roles.add(appRole1);
            roles.add(appRole2);
            g.setRoles(roles);
             /*
            @Acces Globale GESTLV1
             */

        }else if (g.getHabilitation() == 2){

            List<AppRole> roles = new ArrayList<>();
            AppRole appRole = appRoleRepository.findByRoleName("GESTLV1");
            roles.add(appRole);
            g.setRoles(roles);

             /*
            @Acces locale GESTLV2
             */
        }else if (g.getHabilitation() == 1){

            List<AppRole> roles = new ArrayList<>();
            AppRole appRole = appRoleRepository.findByRoleName("GESTLV2");
            roles.add(appRole);
            g.setRoles(roles);

            /*
            sans Acces == DENIED
             */
        }else{

            List<AppRole> roles = new ArrayList<>();
            AppRole appRole = appRoleRepository.findByRoleName("DENIED");
            roles.add(appRole);
            g.setRoles(roles);

        }

        Gestionnaires iogest = gt.getById(id);
        g.setMotDePasse(iogest.getMotDePasse());

        gt.save(g);

        return gt.getById(id);
    }

    @Override
    public ResponseBean supprimerGestionnaire(int g) {

    try {

        dir.deleteAll(gt.getById(g).getDirections());

        gt.delete(gt.getById(g));

        resp.setNumero(200);
        resp.setMessage("Gestionnaire supprimé définitivement avec succes consulter la liste des Gestionnaires");
        resp.setStatus("Good Request");

        return resp;

    }catch (Exception e){

        e.printStackTrace();
        resp.setNumero(500);
        resp.setMessage("Erreur Gestionnaire non disponible veuillez préciser un bon numero");
        resp.setStatus("Bad Request");
    }

        return resp;
    }

    public Optional<Gestionnaires> leGestionnaire(int id) {
        return gt.findById(id);
    }

    @Override
    public Page<Gestionnaires> chercher(String mc, int page, int size) {
        return gt.search(mc, PageRequest.of(page, size));
    }
}
