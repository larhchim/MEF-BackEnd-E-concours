package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.Profils;

import java.util.List;

public interface IProfilsServices {

    public List<Profils> TousLesProfils();

    public Profils addProfils(Profils g);

    public Profils ModifyProfils(Profils g);

    public Profils supprimerProfils(Profils g);

    public Profils leProfil(int id);
}
