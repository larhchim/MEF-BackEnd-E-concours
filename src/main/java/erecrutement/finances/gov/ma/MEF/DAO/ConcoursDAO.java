package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface ConcoursDAO extends JpaRepository<Concours,Integer> {

    @Query("select c from Concours c where c.direction.description like :x or c.direction.fonction like :x or c.intitled like :x or c.direction.nom like :x or c.direction.intitled like :x")
    public Page<Concours> search(@Param("x") String mc, Pageable pageable);

    @Query("select c from Concours c where (c.intitled LIKE :x AND c.direction.intitled like :y ) AND c.etat = true ")
    public Page<Concours> Filter(@Param("x") String intitledcnc,@Param("y") String direc ,Pageable pageable);

    @Query("select p from Concours p left join p.profils q where q.intitled like :x")
    public Page<Concours> Filter2(@Param("x") String intitledprof,Pageable pageable);

    @Modifying
    @Query("UPDATE Concours c SET c.etat = false where c.dateLimiteConcours < current_timestamp ")
    public void Desactivate();

    @Query("select count(c) from Concours c")
    public Integer stats0();

    @Query("select count(c) from Concours c where c.direction  =:x")
    public Integer stats1(@Param("x") Directions directions);

    @Query("select count(c) from Concours c where c.etat  =true and c.direction  =:x")
    public Integer stats2(@Param("x") Directions directions);

    @Query("select count(c) from Concours c where c.etat  =false and c.direction  =:x")
    public Integer stats3(@Param("x") Directions directions);

    @Query("select count(c) from Concours c where c.etat=true")
    public Integer stats4();

    @Query("Select count(d) from Concours d left join d.profils i where i = :item and d.etat =true ")
    public Integer stats5(@Param("item")Profils profils);

    @Query("Select count(d) from Concours d left join d.profils i where i = :item and d.etat =false ")
    public Integer stats6(@Param("item")Profils profils);

}
