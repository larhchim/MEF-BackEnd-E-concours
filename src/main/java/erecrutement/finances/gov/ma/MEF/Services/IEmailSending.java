package erecrutement.finances.gov.ma.MEF.Services;

import erecrutement.finances.gov.ma.MEF.Models.PDF;

import javax.mail.MessagingException;
import java.util.List;

public interface IEmailSending {
    public String sendEmail(String email,String subject,String text);
    public String sendEmailwithAttachment(String email,String subject,String text,String filepath);
    public Boolean Pin(String loging,String password) throws MessagingException;
    public Boolean Recu(PDF pdf) throws MessagingException;
    public String RecoverPassword(String email) throws MessagingException;
    public void EmailOfResults(List<String> emails, String fichier) throws MessagingException;

    }
