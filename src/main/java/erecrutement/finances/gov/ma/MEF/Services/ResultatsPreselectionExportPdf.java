package erecrutement.finances.gov.ma.MEF.Services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import erecrutement.finances.gov.ma.MEF.DAO.ConcoursDAO;
import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Concours;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import erecrutement.finances.gov.ma.MEF.Models.Resultats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ResultatsPreselectionExportPdf {

    private Concours concours;
    private List<Inscriptions> inscriptions;
    private String fichier;



    public ResultatsPreselectionExportPdf(List<Inscriptions> inscriptions,Concours concours,String fic) {
        this.concours = concours;
        this.inscriptions = inscriptions;
        this.fichier =fic;
    }

    public ResultatsPreselectionExportPdf(){

    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    public List<Inscriptions> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscriptions> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public void writeTable(PdfPTable table) {

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.blue);
        font.setSize(20);

        com.lowagie.text.Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font2.setSize(20);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Nom Complet", font));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Adresse CIN", font));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        List<Inscriptions> inscriptions = getInscriptions();


         inscriptions.forEach(r -> {

            PdfPCell cel = new PdfPCell(new Phrase(r.getNomComplet(), font2));
            cel.setBackgroundColor(Color.white);
            cel.setColspan(1);
            cel.setFixedHeight(40f);
            cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cel);

            cel = new PdfPCell(new Phrase(r.getCin(), font2));
            cel.setColspan(1);
            cel.setBackgroundColor(Color.white);
            cel.setFixedHeight(40f);
            cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cel);

        });

    }

    public Document export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String currentDateTime = dateFormatter.format(new Date());
        File file = new File("src/main/resources/static/FilesUploaded/"+getFichier());
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(document, fop);
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.red);

        Image jpeg =  Image.getInstance("src/main/resources/static/FilesUploaded/logo.jpg");
        document.add(jpeg);

        Paragraph p = new Paragraph("Liste des Candidats Convoqués au :"+getConcours().getIntitled(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1f, 1f});
        table.setSpacingBefore(10);
        writeTable(table);
        document.add(table);

        Paragraph p5 = new Paragraph("NB!!!", font);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p5);


        Font font30 = FontFactory.getFont(FontFactory.defaultEncoding);
        font30.setSize(18);


        Paragraph p6 = new Paragraph("La date de passage de votre concours est le "+getConcours().getDatePassage(),font30);
        p6.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(p6);


        Image png =  Image.getInstance("src/main/resources/static/FilesUploaded/tampon.png");
        document.add(png);


        document.close();



        return document;
    }

}

