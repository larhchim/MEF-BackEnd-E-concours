package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ConcoursDAO extends JpaRepository<Concours,Integer> {

    @Query("select c from Concours c where c.direction.description like :x or c.direction.fonction like :x or c.intitled like :x or c.direction.nom like :x or c.direction.intitled like :x")
    public Page<Concours> search(@Param("x") String mc, Pageable pageable);

    @Query("select c from Concours c where (c.intitled LIKE :x AND c.direction.intitled like :y ) AND c.etat = true ")
    public Page<Concours> Filter(@Param("x") String intitledcnc,@Param("y") String direc ,Pageable pageable);

    @Query("select p from Concours p left join p.profils q where q.intitled like :x")
    public Page<Concours> Filter2(@Param("x") String intitledprof,Pageable pageable);

}
