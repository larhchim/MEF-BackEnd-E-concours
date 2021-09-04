package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("select c from Inscriptions c where (c.adresseEmail like :x or c.cin like :z)  and c.concours.idConcours  =:y and c.concours.etat =true ")
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

    @Query("select c from Inscriptions c where c.concours.idConcours=:idc AND (c.cin like :x or c.adresseEmail like :x or c.nomComplet like :x)")
    public Page<Inscriptions> SearchInscriptions(@Param("x") String mc, Pageable pageable,@Param("idc") int idc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat <20")
    public Integer TrancheUnder20();

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=20 and c.ageCandidat <30")
    public Integer TrancheBetween20And30();

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=30 and c.ageCandidat <40")
    public Integer TrancheBetween30And40();

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=40 and c.ageCandidat <50")
    public Integer TrancheBetween40And50();

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=50 and c.ageCandidat <60")
    public Integer TrancheBetween50And60();

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=60")
    public Integer TrancheMoreThan60();


    @Query("select count(c) from Inscriptions c where c.ageCandidat <20 and c.concours.idConcours=:s")
    public Integer TrancheUnder20ByConcours(@Param("s") int cnc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=20 and c.ageCandidat <30 and c.concours.idConcours=:s")
    public Integer TrancheBetween20And30ByConcours(@Param("s") int cnc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=30 and c.ageCandidat <40 and c.concours.idConcours=:s")
    public Integer TrancheBetween30And40ByConcours(@Param("s") int cnc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=40 and c.ageCandidat <50 and c.concours.idConcours=:s")
    public Integer TrancheBetween40And50ByConcours(@Param("s") int cnc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=50 and c.ageCandidat <60 and c.concours.idConcours=:s")
    public Integer TrancheBetween50And60ByConcours(@Param("s") int cnc);

    @Query("select count(c) from Inscriptions c where c.ageCandidat >=60 and c.concours.idConcours=:s")
    public Integer TrancheMoreThan60ByConcours(@Param("s") int cnc);

}
