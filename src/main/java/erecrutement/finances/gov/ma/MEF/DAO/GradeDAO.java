package erecrutement.finances.gov.ma.MEF.DAO;

import erecrutement.finances.gov.ma.MEF.Models.Directions;
import erecrutement.finances.gov.ma.MEF.Models.Grades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDAO extends JpaRepository<Grades,Integer> {
    @Query("select c from Grades c where c.intitledGrade like :x")
    public Page<Grades> search(@Param("x") String mc, Pageable pageable);

    @Query("select c from Grades c where c.intitledGrade like :x")
    public Grades lookfor(@Param("x") String intit);
}
