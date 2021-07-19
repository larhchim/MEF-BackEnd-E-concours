package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;

import java.util.List;
import java.util.Optional;

public interface IGestionnaireService {

    public List<Gestionnaires> TousLesGestionnaires();

    public Optional<Gestionnaires> addGestionnaire(Gestionnaires g);

    public Gestionnaires ModifyGestionnaire(Gestionnaires g);

    public Gestionnaires supprimerGestionnaire(Gestionnaires g);

    public Gestionnaires leGestionnaire(int id);
}
