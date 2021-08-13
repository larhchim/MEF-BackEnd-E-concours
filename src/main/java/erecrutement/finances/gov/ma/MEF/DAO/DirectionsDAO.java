package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Directions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface DirectionsDAO extends JpaRepository<Directions,Integer> {

    @Query("select c from Directions c where c.nom like :x or c.intitled like :x")
    public Page<Directions> search(@Param("x") String mc, Pageable pageable);

}
