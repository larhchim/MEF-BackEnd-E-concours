package erecrutement.finances.gov.ma.MEF.Services;
import erecrutement.finances.gov.ma.MEF.DAO.AppRoleRepository;
import erecrutement.finances.gov.ma.MEF.DAO.GestionnaireDAO;
import erecrutement.finances.gov.ma.MEF.Models.AppRole;
import erecrutement.finances.gov.ma.MEF.Models.Gestionnaires;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService{

    private AppRoleRepository roleRepository;
    private GestionnaireDAO gestionnaireDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppRoleRepository roleRepository, GestionnaireDAO gestionnaireDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.gestionnaireDAO = gestionnaireDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public Gestionnaires loadUserByLogin(String login) {
        return gestionnaireDAO.findByLogin(login);
    }

    @Override
    public void AddRoleToGestionnaire(String login, String roleName) {
        Gestionnaires gestionnaires = loadUserByLogin(login);
        AppRole appRole = roleRepository.findByRoleName(roleName);
        gestionnaires.getRoles().add(appRole);
    }

}
