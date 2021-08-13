package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilDAO extends JpaRepository<Profils,Integer> {
    @Query("select c from Profils c where c.intitled like :x or c.type like :x")
    public Page<Profils> chercher(@Param("x") String mc,  Pageable pageable);
}
