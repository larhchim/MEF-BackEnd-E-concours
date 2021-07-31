package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface GestionnaireDAO extends JpaRepository<Gestionnaires,Integer> {
}
