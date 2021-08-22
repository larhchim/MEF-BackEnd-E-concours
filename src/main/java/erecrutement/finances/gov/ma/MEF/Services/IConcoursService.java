package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IConcoursService {

    public List<Concours> TousLesConcours();

    public Concours addConcours(Concours g);

    public Concours ModifyConcours(Concours g,int aid);

    public ResponseBean supprimerConcours(int g);

    public Optional<Concours> leConcours(int id);

    public Page<Concours> chercher(String mc, int page, int size);

    public Page<Concours> Filter(String intitledcnc, String intitleddir, int pageable,int size);

    public Page<Concours> Filter2(String intitledprof,int page,int size);

    public Concours UnConcours(int id);


}
