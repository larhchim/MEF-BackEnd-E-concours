package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.PDF;
import org.apache.commons.codec.CharEncoding;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

@Service
public class EmailSendingImlp implements IEmailSending{

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String sendEmail(String email,String subject,String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ilarhchim88@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);

        return "Mail sent successfully";
    }

    @Override
    public String sendEmailwithAttachment(String email,String subject,String text,String filepath) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            message.setContent(message,
            "text/html" );

            MimeMessageHelper messageHelper =
                    new MimeMessageHelper(message, true);

            messageHelper.setFrom("ilarhchim88@gmail.com");
            messageHelper.setTo(email);
            addColor(subject, Color.RED);
            messageHelper.setSubject(subject);
            addColor(text, Color.BLUE);
            messageHelper.setText(text);

            File file = new File(filepath);

            messageHelper.addAttachment(file.getName(), file);

            javaMailSender.send(message);

            return "Mail sent successfully";

        } catch (Exception e) {
            return "Mail sent failed";
        }
    }

    @Override
    public Boolean Pin(String loging, String password) throws MessagingException {
        // SimpleMailMessage mailMessage = new SimpleMailMessage();
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true, CharEncoding.UTF_8);

        String mailSubject ="Confirmation Creation Compte Gestionnaires/Administrateur E-Concours";
        String mailContent = "<h2 style=\"text-transform: uppercase;\"><span style= \"color:#F39C12;\">M</span>inistère de l'économie et des finances  MEF</h2>\n";
        mailContent += "<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">pour que vous puissiez s'authentifier vers votre espace Gestionnaire/Administrateur chez Ministère de l'économie et des finances  MEF  veuillez saisir le login suivant en tant que votre username </p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+loging+"</h1>"
                +"<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Ainsi que veuillez saisir ce mot de passe en tant que votre password temporelle pour acceder a votre compte Gestionnaire/Administrateur de la MEF vous pouvez le changer ultérieurement </p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+password+"</h1>";

        mailContent+="<span style= \"font-weight: 300; color: #111;font-size: 19px;font-family:Trebuchet MS, sans-serif;\">si vous avez un problème vous pouvez contacter le support dans le lien suivant <a href='mailto:ismaillarhchim864@gmail.com'> MEFRA dans votre Service</a>"
                + "</span>";
        helper.setTo(loging);
        helper.setFrom("ilarhchim88@gmail.com");

        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);

        File file = new File("src/main/resources/static/FilesUploaded/logo.jpg");

        helper.addAttachment(file.getName(), file);

        javaMailSender.send(mailMessage);
        return true;
    }

    @Override
    public Boolean Recu(PDF pdf) throws MessagingException {
        // SimpleMailMessage mailMessage = new SimpleMailMessage();
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true, CharEncoding.UTF_8);

        String mailSubject ="Confirmation Candidature au "+pdf.getConcours();
        String mailContent = "<h2 style=\"text-transform: uppercase;\"><span style= \"color:#F39C12;\">M</span>inistére de l'économie et des finances  MEF</h2>\n";
        mailContent += "<p style= \"font-weight: 400; color: black;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">On vous Confirme que votre candidature a ete effectuée avec succees ci dessous le détail de votre recu: </p>"
                +"<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Nom Complet:</p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+pdf.getNomComplet()+"</h1>"
                +"<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Numero CIN:</p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+pdf.getCin()+"</h1>"
                +"<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Numero Inscription:</p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+pdf.getNumeroInscription()+"</h1>"
                +"<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Concours:</p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+pdf.getConcours()+"</h1>";

        mailContent+="<p style= \"font-weight: 300; color: #111;font-size: 19px;font-family:Trebuchet MS, sans-serif;\">L'affectation au Centre de votre concours vous sera communiqué par mail ultérieurement veuillez verifier votre boite email de temps en temps"
                + "</p>";
        mailContent+="<p style= \"font-weight: 300; color: #111;font-size: 19px;font-family:Trebuchet MS, sans-serif;\">si vous avez un problème vous pouvez contacter le support dans le lien suivant <a href='mailto:ismaillarhchim864@gmail.com'> MEFRA dans votre Service</a>"
                + "</p>";

        helper.setTo(pdf.getEmail());
        helper.setFrom("ilarhchim88@gmail.com");

        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);

        File file = new File("src/main/resources/static/FilesUploaded/logo.jpg");
        helper.addAttachment(file.getName(), file);
        File f = new File("src/main/resources/static/FilesUploaded/"+pdf.getNomComplet()+"_"+pdf.getNumeroInscription()+"_"+pdf.getCin()+"-"+pdf.getNumeroc()+".pdf");

        helper.addAttachment(f.getName(),f);

        javaMailSender.send(mailMessage);
        return true;
    }

    @Override
    public String RecoverPassword(String email) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true, CharEncoding.UTF_8);

        String pin =alphaNumericString(10);

        String mailSubject ="Récuperation mot de passe du Compte Gestionnaires/Administrateur E-Concours";
        String mailContent = "<h2 style=\"text-transform: uppercase;\"><span style= \"color:#F39C12;\">M</span>inistère de l'économie et des finances  MEF</h2>\n";
        mailContent += "<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Votre code pin de restauration du compte E-concours est:</p>"
                +"<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">"+pin+"</h1>";

        mailContent+="<span style= \"font-weight: 300; color: #111;font-size: 19px;font-family:Trebuchet MS, sans-serif;\">si vous avez un problème vous pouvez contacter le support dans le lien suivant <a href='mailto:ismaillarhchim864@gmail.com'> MEFRA dans votre Service</a>"
                + "</span>";
        helper.setTo(email);
        helper.setFrom("ilarhchim88@gmail.com");

        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);

        File file = new File("src/main/resources/static/FilesUploaded/logo.jpg");

        helper.addAttachment(file.getName(), file);

        javaMailSender.send(mailMessage);
        return pin;
    }

    @Override
    public void EmailOfResults(List<String> emails, String fichier) throws MessagingException {

        emails.forEach(r->{
            System.out.println(r);
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(mailMessage,true, CharEncoding.UTF_8);

                String mailSubject ="Message d'information de preselection dans le concours de la MERFA";
                String mailContent = "<h2 style=\"text-transform: uppercase;\"><span style= \"color:#F39C12;\">M</span>inistère de l'économie et des finances  MEF</h2>\n";
                mailContent += "<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">vous informe que vous etes selectionée pour passer le concours ecrit qui aura lieu A Rabat ci joint le document de la preselection</p>";

                helper.setTo(r);
                helper.setFrom("ilarhchim88@gmail.com");

                helper.setSubject(mailSubject);
                helper.setText(mailContent,true);

                File file = new File("src/main/resources/static/FilesUploaded/logo.jpg");

                helper.addAttachment(file.getName(), file);

                File file2 = new File("src/main/resources/static/FilesUploaded/"+fichier);

                helper.addAttachment(file2.getName(), file2);

                javaMailSender.send(mailMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }


        });

    }


    public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String addColor(String msg, Color color) {
        String hexColor = String.format("#%06X",  (0xFFFFFF & color.getRGB()));
        String colorMsg = "<FONT COLOR=\"#" + hexColor + "\">" + msg + "</FONT>";
        return colorMsg;
    }

}
