package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;

import java.util.List;
import java.util.Optional;

public interface IConcoursService {

    public List<Concours> TousLesConcours();

    public Concours addConcours(Concours g);

    public Concours ModifyConcours(Concours g,int aid);

    public ResponseBean supprimerConcours(int g);

    public Optional<Concours> leConcours(int id);

}