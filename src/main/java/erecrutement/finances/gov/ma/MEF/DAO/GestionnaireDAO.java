package erecrutement.finances.gov.ma.MEF.DAO;

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
}
