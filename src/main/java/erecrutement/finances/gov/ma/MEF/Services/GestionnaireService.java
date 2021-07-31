package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GestionnaireService implements IGestionnaireService{

    private GestionnaireDAO gt;

    private DirectionsDAO dir;

    private IMd5Hash md5;

    private IEmailSending email;


    @Autowired
    public void setGt(GestionnaireDAO gt) {
        this.gt = gt;
    }

    @Autowired
    public void setDir(DirectionsDAO dir) {
        this.dir = dir;
    }

    @Autowired
    public void setMd5(IMd5Hash md5) {
        this.md5 = md5;
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
        /*List<Directions> dr = g.getDirections();
        //dr.add(dir.getById(1));
        g.setDirections(dr);*/

        String mot = EmailSendingImlp.alphaNumericString(10);
        String pass = md5.getMd5(mot);

        g.setMotDePasse(pass);

        /*
        Sending true pass through email adress g.getEmail
         */

        email.sendEmail(g.getLogin(),"E-concours platform Authentification Credentials",
                "" +"These are your credentials for authentification <br/>"+
                        "Login: "+g.getLogin()+"" +
                        "Password: "+mot+"");

        g.setMotDePasse(pass);
        gt.save(g);
        int id = g.getIdGestionnaire();
        return gt.findById(id);
    }

    @Override
    public Gestionnaires ModifyGestionnaire(Gestionnaires g,int id) {
        /*List<Directions> dr = g.getDirections();
        dr.add(dir.getById(1));

        System.out.println(dr);*/

        g.setIdGestionnaire(id);
        //g.setDirections(dr);

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
}
