package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.HistoriqueChangementStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoryStatusCandidateDAO extends JpaRepository<HistoriqueChangementStatus,Integer> {
    @Query("select c from HistoriqueChangementStatus c WHERE c.inscription.idInscription =:idc")
    public Page<HistoriqueChangementStatus> search(Pageable pageable,@Param("idc") int id);
}
