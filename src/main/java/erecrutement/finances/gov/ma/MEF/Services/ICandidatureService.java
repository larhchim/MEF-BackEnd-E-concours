package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICandidatureService {
    public Inscriptions AddInscription(Inscriptions inscriptions,int id);
    public List<Inscriptions> GetAllInscriptions();
}

