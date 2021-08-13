package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Profils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProfilsServices {

    public List<Profils> TousLesProfils();

    public Optional<Profils> addProfils(Profils g);

    public ResponseEntity<Profils> ModifyProfils(Profils g);

    public Profils supprimerProfils(Profils g);

    public Profils leProfil(int id);

    public Page<Profils> chercher(String mc,int size,int page);
}
