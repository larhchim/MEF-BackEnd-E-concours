package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Concours;

import java.util.List;
import java.util.Optional;

public interface IConcoursService {

    public List<Concours> TousLesConcours();

    public Concours addConcours(Concours g);

    public Optional<Concours> ModifyConcours(Concours g);

    public Concours supprimerConcours(Concours g);

    public Concours leConcours(int id);

}
