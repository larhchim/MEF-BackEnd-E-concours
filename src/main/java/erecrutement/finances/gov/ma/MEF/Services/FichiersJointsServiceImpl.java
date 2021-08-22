package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.DAO.FichiersJointsDAO;
import erecrutement.finances.gov.ma.MEF.Models.FichiersJoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FichiersJointsServiceImpl implements IFichiersJointsService{


    private FichiersJointsDAO iFichiersJointsService;

    @Autowired
    public void setiFichiersJointsService(FichiersJointsDAO iFichiersJointsService) {
        this.iFichiersJointsService = iFichiersJointsService;
    }

    @Override
    public void AddFichiersJoints(FichiersJoints fichiers) {
        iFichiersJointsService.save(fichiers);
    }
}
