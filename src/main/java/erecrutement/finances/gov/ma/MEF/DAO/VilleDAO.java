package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleDAO extends JpaRepository<Ville,Integer> {

    @Query("select c from Ville c where c.nomVille like :x")
    public Page<Ville> search(@Param("x") String mc, Pageable pageable);


}
