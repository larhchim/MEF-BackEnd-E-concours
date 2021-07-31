package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import erecrutement.finances.gov.ma.MEF.Models.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IController<T> {

    /*
    @GetMapping(path="list",produces= {"application/json"})
     */
    public List<T> ListeObjects();


    /*
    @PostMapping(path = "add",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
     */
    public Optional<T> AddObject(@RequestBody T r);

    /*
    @GetMapping(path="obj/{aid}",produces= {"application/json"})
     */
    public Optional<T> ObjectById(@PathVariable int id);

    /*
    @DeleteMapping("deleteObj/{aid}")
     */
    public ResponseBean DeleteObject(@PathVariable int id);

    /*
    @PutMapping(path="UpdateObj/{aid}",consumes = {"application/json;charset=UTF-8"} ,produces = {"application/json;charset=UTF-8"})
     */
    public ResponseEntity<T> ModifyObject(@RequestBody T cnc, @PathVariable int id);

}
