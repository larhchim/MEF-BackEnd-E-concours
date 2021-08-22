package erecrutement.finances.gov.ma.MEF.Services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import erecrutement.finances.gov.ma.MEF.DAO.ConcoursDAO;
import erecrutement.finances.gov.ma.MEF.DAO.DirectionsDAO;
import erecrutement.finances.gov.ma.MEF.DAO.GradeDAO;
import erecrutement.finances.gov.ma.MEF.DAO.ProfilDAO;
import erecrutement.finances.gov.ma.MEF.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConcoursService implements IConcoursService{

    private ConcoursDAO cnc;

    private ProfilDAO pfl;

    private GradeDAO grd;

    private DirectionsDAO dir;

    @Autowired
    public void setCnc(ConcoursDAO cnc) {
        this.cnc = cnc;
    }

    @Autowired
    public void setPfl(ProfilDAO pfl) {
        this.pfl = pfl;
    }

    @Autowired
    public void setGrd(GradeDAO grd) {
        this.grd = grd;
    }

    @Autowired
    public void setDir(DirectionsDAO dir) {
        this.dir = dir;
    }

    ResponseBean resp = new ResponseBean();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Concours> TousLesConcours() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
           /* String json = mapper.writeValueAsString(cnc.findAll());
            System.out.println(json);*/
//JsonProcessingException
        } catch (Exception e) {
            e.printStackTrace();

        }
        return cnc.findAll();

    }

    @Override
    public Concours addConcours(Concours g) {
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            //g.setDirection(dir.getById(1));
            cnc.save(g);

            /*List<Profils> t = g.getProfils();
            for (Profils p:t) {
                p.setConcours(g);
                pfl.save(p);
            }*/




        }catch (Exception e){
            e.printStackTrace();
        }
        return cnc.getById(g.getIdConcours());
    }

    @Override
    public Concours ModifyConcours(Concours g,int aid) {
        try{

            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
           // Concours cn = cnc.getById(aid);
            g.setIdConcours(aid);
            //g.setDirection(cn.getDirection());


            cnc.save(g);

           /* for (Profils f:g.getProfils()) {
                f.setConcours(g);
                pfl.save(f);
            }*/
           /* List<Profils> t = g.getProfils();
            for (Profils p:t) {
                for (Grades r:p.getGrades()) {
                    grd.save(r);
                }
                p.setConcours(g);
                pfl.save(p);
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
        return cnc.getById(g.getIdConcours());
    }

    @Override
    public ResponseBean supprimerConcours(int g) {
        try{
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
System.out.println("lwla daz***");
            Concours a = cnc.getById(g);

            List<Profils> p = a.getProfils();

            for (Profils f:p) {
                grd.deleteAll(f.getGrades());
                pfl.delete(f);
            }

            System.out.println("tanya daz***");


            /*a.setDirection(null);
            cnc.save(a);*/

            System.out.println("talta daz***");


            cnc.delete(cnc.getById(g));

            System.out.println("rab3a daz***");

            resp.setNumero(200);
            resp.setMessage("Concours supprimé définitivement avec succes consulter la liste des concouurs");
            resp.setStatus("Good Request");

            System.out.println("khamssa daz***");

            return resp;

        }catch (Exception e){

            e.printStackTrace();
            resp.setNumero(500);
            resp.setMessage("Erreur Concours non disponible veuillez préciser un bon numero");
            resp.setStatus("Bad Request");
        }

        return resp;
    }

    @Override
    public Optional<Concours> leConcours(int id) {
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            /*String json = mapper.writeValueAsString(cnc.findAll());
            System.out.println(json);*/

        } catch (Exception e) {
           e.printStackTrace();

        }
        return cnc.findById(id);
    }

    @Override
    public Page<Concours> chercher(String mc, int page, int size) {
        return cnc.search(mc, PageRequest.of(page,size));
    }

    @Override
    public Page<Concours> Filter(String intitledcnc, String intitleddir,int page,int size) {
        return cnc.Filter(intitledcnc,intitleddir,PageRequest.of(page, size));
    }

    @Override
    public Page<Concours> Filter2(String intitledprof, int page, int size) {
        return cnc.Filter2(intitledprof,PageRequest.of(page,size));
    }

    @Override
    public Concours UnConcours(int id) {
        return cnc.getById(id);
    }
}
