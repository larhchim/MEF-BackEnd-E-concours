package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;

import java.util.List;
import java.util.Optional;

public interface IGestionnaireService {

    public List<Gestionnaires> TousLesGestionnaires();

    public Optional<Gestionnaires> addGestionnaire(Gestionnaires g);

    public Gestionnaires ModifyGestionnaire(Gestionnaires g,int id);

    public ResponseBean supprimerGestionnaire(int g);

    public Optional <Gestionnaires> leGestionnaire(int id);
}
