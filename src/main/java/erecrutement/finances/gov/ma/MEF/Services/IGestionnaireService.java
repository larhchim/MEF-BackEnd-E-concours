package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Examinateurs;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IGestionnaireService {

    public List<Gestionnaires> TousLesGestionnaires();

    public Optional<Gestionnaires> addGestionnaire(Gestionnaires g);

    public Gestionnaires ModifyGestionnaire(Gestionnaires g,int id);

    public ResponseBean supprimerGestionnaire(int g);

    public Optional <Gestionnaires> leGestionnaire(int id);

    public Page<Gestionnaires> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    );

    public List<Examinateurs> EXAMINATEURS_LIST();
}
