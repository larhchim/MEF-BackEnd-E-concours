package erecrutement.finances.gov.ma.MEF.Services;
import java.util.List;
import java.util.Optional;

public interface InterfaceService <T> {

    public List<T> TousLesObjets();

    public Optional<T> addObjet(T g);

    public T ModifyObjet(T g,int aid);

    public Optional<T> leComposant(int id);

}
