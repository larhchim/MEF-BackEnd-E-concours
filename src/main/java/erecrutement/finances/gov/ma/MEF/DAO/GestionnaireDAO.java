package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionnaireDAO extends JpaRepository<Gestionnaires,Integer> {
}
