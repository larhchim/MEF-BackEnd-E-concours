package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcoursDAO extends JpaRepository<Concours,Integer> {

    @Query("select c from Concours c where c.direction.description like :x or c.direction.fonction like :x or c.intitled like :x or c.direction.nom like :x or c.direction.intitled like :x")
    public Page<Concours> search(@Param("x") String mc, Pageable pageable);

}
