package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Resultats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatsDAO extends JpaRepository<Resultats,Integer> {
    @Query("select c from Resultats c where c.concours.idConcours =:r")
    public Resultats leResultat(@Param("r") int res);
}
