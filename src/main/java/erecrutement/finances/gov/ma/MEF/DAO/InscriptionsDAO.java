package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
@Transactional
public interface InscriptionsDAO extends JpaRepository<Inscriptions,Integer> {
    @Query("select c from Inscriptions c where (c.adresseEmail like :x or c.cin like :z)  and c.concours.idConcours  =:y")
    public Inscriptions Verification(@Param("z") String cin, @Param("y") int numero, @Param("x") String email);

    @Query("select c from Inscriptions c where c.idInscription < 10")
    public List<Inscriptions> DownloadOne();

    @Query("select count(c) from Inscriptions c ")
    public Integer stats1();

    @Query("select count(c) from Inscriptions c where c.concours =:x")
    public Integer stats2(@Param("x")Concours concours);

    @Query("select count(c) from Inscriptions c where c.etatCandidature=true ")
    public Integer stats3();

    @Query("select count(c) from Inscriptions c where c.etatCandidature=false ")
    public Integer stats4();

    @Query("select count(c) from Inscriptions c where c.etatCandidature=true and c.concours =:x ")
    public Integer stats5(@Param("x")Concours concours);

    @Query("select count(c) from Inscriptions c where c.etatCandidature=false and c.concours =:x")
    public Integer stats6(@Param("x")Concours concours);
}
