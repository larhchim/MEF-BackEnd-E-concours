package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;

public interface IAccountService {
    public AppRole saveRole(AppRole role);
    public Gestionnaires loadUserByLogin(String login);
    public void AddRoleToGestionnaire(String login,String roleName);
}
