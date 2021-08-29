package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface GestionnaireDAO extends JpaRepository<Gestionnaires,Integer> {

    @Query("select c from Gestionnaires c where c.login like :x")
    public Page<Gestionnaires> search(@Param("x") String mc, Pageable pageable);

    public Gestionnaires findByLogin(String login);

    @Query("Select count(d) from Gestionnaires d where d.etatcCompte=false ")
    public Integer stats0();

    @Query("select count(c) from Gestionnaires c")
    public Integer Stats1();

    @Query("select count(c) from Gestionnaires c where c.isAdministrator = true ")
    public Integer Stats2();

    @Query("select count(c) from Gestionnaires c where c.isAdministrator =false AND c.habilitation=1")
    public Integer Stats3();

    @Query("select count (c) from Gestionnaires c where c.isAdministrator = false  AND  c.habilitation =2")
    public Integer Stats4();

    @Query("Select count(d) from Gestionnaires d left join d.directions i where i = :item ")
    public Integer stats5(@Param("item") Directions directions );

    @Query("Select count(d) from Gestionnaires d left join d.directions i where i = :item AND d.isAdministrator =true ")
    public Integer stats6(@Param("item") Directions directions );

    @Query("Select count(d) from Gestionnaires d left join d.directions i where i = :item AND d.isAdministrator =false And d.habilitation=1")
    public Integer stats7(@Param("item") Directions directions );

    @Query("Select count(d) from Gestionnaires d left join d.directions i where i = :item AND d.isAdministrator =false And d.habilitation=2")
    public Integer stats8(@Param("item") Directions directions );

   @Query("Select count(d) from Gestionnaires d left join d.directions i where i = :item and d.etatcCompte=false ")
   public Integer stats9(@Param("item") Directions directions);



}
