package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.Centres;
import erecrutement.finances.gov.ma.MEF.Models.Directions;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface DirExtension {

    public Directions Search(String mc);

    public ResponseEntity<Object> createOne(@RequestBody @Valid Centres centres, BindingResult bindingResult);
}
