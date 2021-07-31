package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.HistoriqueGestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueGestionnaireDAO extends JpaRepository<HistoriqueGestionnaire,Integer> {
}
