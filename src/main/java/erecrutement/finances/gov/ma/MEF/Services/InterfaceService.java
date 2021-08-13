package erecrutement.finances.gov.ma.MEF.Services;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface InterfaceService <T> {

    public List<T> TousLesObjets();

    public Optional<T> addObjet(T g);

    public T ModifyObjet(T g,int aid);

    public Optional<T> leComposant(int id);

    public Page<T> chercher(String mc, int page, int size);

}
