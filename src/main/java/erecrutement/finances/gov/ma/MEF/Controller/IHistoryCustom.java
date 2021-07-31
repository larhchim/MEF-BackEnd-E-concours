package erecrutement.finances.gov.ma.MEF.Controller;

import erecrutement.finances.gov.ma.MEF.Models.HistoriqueGestionnaire;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface IHistoryCustom {

    public Optional<HistoriqueGestionnaire> AddObject(@RequestBody HistoriqueGestionnaire r, @PathVariable int id);
}
