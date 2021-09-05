package erecrutement.finances.gov.ma.MEF.Services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import erecrutement.finances.gov.ma.MEF.DAO.InscriptionsDAO;
import erecrutement.finances.gov.ma.MEF.Models.Inscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PDFExporterService {

    private int NumeroInscription;
    private String NomComplet;
    private String Email;
    private String Cin;
    private String Concours;
    private int numerocnc;
    private Date date;
    private String intitule;

    public PDFExporterService(int numeroInscription, String nomComplet, String email, String cin, String concours,int numerocnc,Date date,String intitule) {
        NumeroInscription = numeroInscription;
        NomComplet = nomComplet;
        Email = email;
        Cin = cin;
        Concours = concours;
        this.numerocnc =numerocnc;
        this.date = date;
        this.intitule = intitule;
    }

    public PDFExporterService(){

    }


    public int getNumeroInscription() {
        return NumeroInscription;
    }

    public void setNumeroInscription(int numeroInscription) {
        NumeroInscription = numeroInscription;
    }

    public String getNomComplet() {
        return NomComplet;
    }

    public void setNomComplet(String nomComplet) {
        NomComplet = nomComplet;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getConcours() {
        return Concours;
    }

    public void setConcours(String concours) {
        Concours = concours;
    }

    public int getNumerocnc() {
        return numerocnc;
    }

    public void setNumerocnc(int numerocnc) {
        this.numerocnc = numerocnc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    private void writeTableHeader(PdfPTable table) {

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.blue);
        font.setSize(20);

        com.lowagie.text.Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font2.setSize(20);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Direction du Ministère",font));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""+getIntitule(),font2));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Votre Nom Complet",font));
        cell.setBackgroundColor(Color.white);
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(NomComplet,font2));
        cell.setColspan(1);
        cell.setBackgroundColor(Color.white);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Votre Email",font));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Email,font2));
        cell.setColspan(1);
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Votre Cin",font));
        cell.setBackgroundColor(Color.white);
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(Cin,font2));
        cell.setColspan(1);
        cell.setBackgroundColor(Color.white);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);



        cell = new PdfPCell(new Phrase("Votre Concours",font));
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setColspan(1);
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Concours,font2));
        cell.setColspan(1);
        cell.setBackgroundColor(Color.getHSBColor(240, 240, 240));
        cell.setFixedHeight(70f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);



       /* PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Numero Inscription", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();

        cell.setPhrase(new Phrase("Nom Complet", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();

        cell.setPhrase(new Phrase("E-mail", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();

        cell.setPhrase(new Phrase("CIN", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();

        cell.setPhrase(new Phrase("Adresse", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();

        cell.setPhrase(new Phrase("Année Diplome", font));
        cell.setColspan(2);
        table.addCell(cell);
        table.completeRow();*/

    }


    public Document export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        File file = new File("src/main/resources/static/FilesUploaded/"+NomComplet+"_"+NumeroInscription+"_"+Cin+"-"+numerocnc+".pdf");
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(document, fop);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.red);

        Image jpeg =  Image.getInstance("src/main/resources/static/FilesUploaded/logo.jpg");
        document.add(jpeg);



        Paragraph p = new Paragraph("Inscription Numero:"+NumeroInscription, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1f, 1f});

        table.setSpacingBefore(10);

        writeTableHeader(table);
        //writeTableData(table);

        document.add(table);

        Font font3 = FontFactory.getFont(FontFactory.defaultEncoding);
        font3.setSize(15);


        Paragraph io = new Paragraph();
        io.setAlignment(Paragraph.ALIGN_CENTER);
        io.setLeading(15);
        io.setExtraParagraphSpace(15);
        io.setSpacingBefore(15);
        document.add(io);

        Paragraph iio = new Paragraph();
        iio.setAlignment(Paragraph.ALIGN_CENTER);
        iio.setLeading(15);
        iio.setExtraParagraphSpace(15);
        iio.setSpacingBefore(15);
        document.add(iio);

        font3.setColor(Color.GREEN.darker());

        Paragraph p4 = new Paragraph("Attention!!!", font);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p4);

        Paragraph p2 = new Paragraph("Ce document est une confirmation de la candidature dans la plateforme E-concours du Ministère de la finance et de la reforme de l'administration Veuillez se presenter le jour de l'examen muni de ce formulaire imprimé Merci.",font3);
        p2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(p2);

        Paragraph p5 = new Paragraph("NB!!!", font);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p5);


        Font font30 = FontFactory.getFont(FontFactory.defaultEncoding);
        font30.setSize(18);


        Paragraph p6 = new Paragraph("La date de passage de votre concours est le "+getDate(),font30);
        p6.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(p6);


        Image png =  Image.getInstance("src/main/resources/static/FilesUploaded/tampon.png");
        document.add(png);


        document.close();



        return  document;

    }


}
