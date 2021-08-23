package erecrutement.finances.gov.ma.MEF.Services;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.awt.*;
import java.io.File;
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
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

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

        javaMailSender.send(mailMessage);
        return true;
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
