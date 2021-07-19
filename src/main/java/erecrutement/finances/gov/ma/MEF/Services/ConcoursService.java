package erecrutement.finances.gov.ma.MEF.Services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import erecrutement.finances.gov.ma.MEF.DAO.ConcoursDAO;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcoursService implements IConcoursService{

    @Autowired
    ConcoursDAO cnc;

    @Override
    public List<Concours> TousLesConcours() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String json = mapper.writeValueAsString(cnc.findAll());
            System.out.println(json);

        } catch (JsonProcessingException e) {
        }
        return cnc.findAll();

    }

    @Override
    public Concours addConcours(Concours g) {
        cnc.save(g);
        return g;
    }

    @Override
    public Optional<Concours> ModifyConcours(Concours g) {
        cnc.save(g);
        int id = g.getIdConcours();
        return cnc.findById(id);
    }

    @Override
    public Concours supprimerConcours(Concours g) {
        cnc.delete(g);
        return g;
    }

    @Override
    public Concours leConcours(int id) {
        return cnc.getById(id);
    }
}
