package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeDAO extends JpaRepository<Grades,Integer> {
}
