package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Ville;
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

    public Centres findCentresByAdresseAndVilleAndSalle(String Adresse, Ville Ville, String Salle );

    @Query("Select count(d) from Centres d left join d.concours i where i = :item ")
    public Integer stats1(@Param("item")Concours concours);

    @Query("select count(d) from Centres d")
    public Integer stats2();

}
