package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.FichiersJoints;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CandidatureServiceImpl implements ICandidatureService{

    private InscriptionsDAO insc;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private IConcoursService concoursService;

    private IFichiersJointsService files;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setFiles(IFichiersJointsService files) {
        this.files = files;
    }

    @Autowired
    public void setConcoursService(IConcoursService concoursService) {
        this.concoursService = concoursService;
    }

    @Autowired
    public void setInsc(InscriptionsDAO insc) {
        this.insc = insc;
    }



    @Override
    public Inscriptions AddInscription(Inscriptions inscriptions, int id) {
        inscriptions.setDateCandidature(new Date());
        inscriptions.setMotDePasse(bCryptPasswordEncoder.encode(inscriptions.getMotDePasse()));
        inscriptions.setRemotDePasse(bCryptPasswordEncoder.encode(inscriptions.getRemotDePasse()));
        inscriptions.setAdresse(inscriptions.getAdresse());
        inscriptions.setConcours(concoursService.UnConcours(id));
        inscriptions.setFichiers(inscriptions.getFichiers());
        for (FichiersJoints fic : inscriptions.getFichiers()) {
            fic.setInscription(inscriptions);
           files.AddFichiersJoints(fic);
        }
        System.out.println(inscriptions.getFichiers());
        return insc.save(inscriptions) ;
    }

    @Override
    public List<Inscriptions> GetAllInscriptions() {
        return insc.findAll();
    }
}
