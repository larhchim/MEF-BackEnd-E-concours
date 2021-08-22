package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Grades;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.IGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestGradesController implements IController<Grades>,IGradeExtension{

    private IGradesService grade;

    @Autowired
    public void setGrade(IGradesService grade) {
        this.grade = grade;
    }

    @Override
    @GetMapping(path="ALLGrades",produces= {"application/json"})
    public List<Grades> ListeObjects() {
        return grade.TousLesGrades();
    }

    @Override
    @PostMapping(path = "AddGrade",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public Optional<Grades> AddObject(Grades r) {
        return grade.addGrades(r);
    }

    @Override
    public Optional<Grades> ObjectById(int id) {
        return Optional.empty();
    }

    @Override
    public ResponseBean DeleteObject(int id) {
        return null;
    }

    @Override
    @PutMapping(path="UpdateGrade/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Grades> ModifyObject(Grades cnc, int id) {
        cnc.setId(id);
        return new ResponseEntity<Grades>(grade.ModifyGrades(cnc), HttpStatus.OK);
    }

    @Override
    @GetMapping(path="SearchGrade",produces= {"application/json"})
    public Page<Grades> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        return grade.chercher("%"+mc+"%",page,size);
    }

    @Override
    @GetMapping(path = "ProfilsManqueGrade/{id}",produces= {"application/json"})
    public List<Profils> ProfilsManqueGrade(@PathVariable int id) {
        return grade.ProfilsManqueGrade(id);
    }
}
