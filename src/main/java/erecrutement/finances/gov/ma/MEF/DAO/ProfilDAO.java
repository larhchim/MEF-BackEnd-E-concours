package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilDAO extends JpaRepository<Profils,Integer> {
}
