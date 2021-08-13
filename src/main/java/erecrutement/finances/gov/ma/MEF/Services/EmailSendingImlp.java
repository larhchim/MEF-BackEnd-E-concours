package erecrutement.finances.gov.ma.MEF.Services;

import org.springframework.stereotype.Service;

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
