package erecrutement.finances.gov.ma.MEF.Controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.DAO.ResultatsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import erecrutement.finances.gov.ma.MEF.Models.PDF;
import erecrutement.finances.gov.ma.MEF.Models.Resultats;
import erecrutement.finances.gov.ma.MEF.Services.IConcoursService;
import erecrutement.finances.gov.ma.MEF.Services.IEmailSending;
import erecrutement.finances.gov.ma.MEF.Services.PDFExporterService;
import erecrutement.finances.gov.ma.MEF.Services.ResultatsPreselectionExportPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class RestPdfExporter {

    private InscriptionsDAO inscriptionsDAO;
    private IEmailSending iEmailSending;
    private IConcoursService iConcoursService;
    private ResultatsDAO resultatsDAO;

    @Autowired
    public void setResultatsDAO(ResultatsDAO resultatsDAO) {
        this.resultatsDAO = resultatsDAO;
    }

    @Autowired
    public void setiConcoursService(IConcoursService iConcoursService) {
        this.iConcoursService = iConcoursService;
    }

    @Autowired
    public void setiEmailSending(IEmailSending iEmailSending) {
        this.iEmailSending = iEmailSending;
    }

    @Autowired
    public void setInscriptionsDAO(InscriptionsDAO inscriptionsDAO) {
        this.inscriptionsDAO = inscriptionsDAO;
    }

    @PostMapping(path = "/users/export/pdf/{id}",consumes = {"application/json;charset=UTF-8"})
    public void exportToPDF(HttpServletResponse response, @RequestBody PDF pdf, @PathVariable("id") int id) throws DocumentException, IOException {

        Concours c = iConcoursService.UnConcours(id);
        Date ee =c.getDatePassage();
        String r=c.getDirection().getIntitled();
        pdf.setNumeroc(c.getIdConcours());


        response.setContentType("application/pdf");
        System.out.println(pdf);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        PDFExporterService exporter = new PDFExporterService(pdf.getNumeroInscription(),pdf.getNomComplet(),pdf.getEmail(),pdf.getCin(),pdf.getConcours(),id,ee,r);
        exporter.export(response);

        try {
            iEmailSending.Recu(pdf);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    @GetMapping  (path = "/users/export/pdf/Resultats/{id}",consumes = {"application/json;charset=UTF-8"})
    public ResponseEntity<Object> exportResultsToPDF(HttpServletResponse response, @PathVariable("id") int id) throws DocumentException, IOException {

        Resultats resultatss =resultatsDAO.leResultat(id);
        Map<String,String> map = new HashMap<>();
        if (resultatss!=null){
            map.put("error","Resultats deja existent pour ce concours");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }

        Concours c = iConcoursService.UnConcours(id);
        List<Inscriptions> inscriptions = inscriptionsDAO.ListeDesPreselectionnes(id);

        List<String> emails = new ArrayList<>();

        inscriptions.forEach(r->{
            emails.add(r.getAdresseEmail());
        });

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        DateFormat dateFormatterr = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String currentDateTimer = dateFormatterr.format(new Date());
        File file = new File("src/main/resources/static/FilesUploaded/"+c.getIntitled()+"_"+currentDateTimer+".pdf");
        ResultatsPreselectionExportPdf exporter = new ResultatsPreselectionExportPdf(inscriptions,c,file.getName());
        exporter.export(response);



        Concours cnc = c;
        Resultats resultats =new Resultats();
        resultats.setConcours(cnc);
        resultats.setNombrePostes(cnc.getNombrePostes());
        resultats.setDateConcours(cnc.getDateConcours());
        resultats.setCandidatsConvoques(file.getName());
        cnc.setResultats(resultats);
        iConcoursService.addConcours(cnc);


        try {
            iEmailSending.EmailOfResults(emails,file.getName());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Concours co = iConcoursService.UnConcours(id);


       return new ResponseEntity<>(co.getResultats(),HttpStatus.OK);

    }



}
