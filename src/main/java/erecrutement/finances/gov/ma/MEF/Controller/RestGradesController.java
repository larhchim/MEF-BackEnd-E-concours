package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.DAO.GradeDAO;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.Grades;
import erecrutement.finances.gov.ma.MEF.Models.Profils;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import erecrutement.finances.gov.ma.MEF.Services.IGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestGradesController implements IController<Grades>,IGradeExtension{

    private IGradesService grade;
    private GradeDAO gradeDAO;

    @Autowired
    public void setGradeDAO(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

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
    @PostMapping(path = "AddGrade",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> AddObjectExtension(@Valid Grades grades, BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        Grades c = gradeDAO.lookfor(grades.getIntitledGrade());


        if (c!=null){
            errors.put("error","Grade Already Exists");
            return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(grade.addGrades(grades),HttpStatus.OK);
    }

    @Override
    @PutMapping(path="UpdateGrade/{id}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> UpdateObjectExtension(@Valid Grades grades, BindingResult bindingResult,@PathVariable("id") int id) {
        Map<String,String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError fd:bindingResult.getFieldErrors()) {
                errors.put(fd.getField(), fd.getDefaultMessage());
            }

            return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(grade.ModifyGrades(grades),HttpStatus.OK);    }

    @Override
    @GetMapping(path = "ProfilsManqueGrade/{id}",produces= {"application/json"})
    public List<Profils> ProfilsManqueGrade(@PathVariable int id) {
        return grade.ProfilsManqueGrade(id);
    }
}
