package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Centres;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CentresDAO extends JpaRepository<Centres,Integer> {

    @Query("SELECT c from Centres c where c.adresse like :x or c.salle like :x or c.ville.nomVille like :x")
    public Page<Centres> search(@Param("x") String mc, Pageable pageable);

}
