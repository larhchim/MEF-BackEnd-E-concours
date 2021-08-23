package erecrutement.finances.gov.ma.MEF.Services;

import javax.mail.MessagingException;

public interface IEmailSending {
    public String sendEmail(String email,String subject,String text);
    public String sendEmailwithAttachment(String email,String subject,String text,String filepath);
    public Boolean Pin(String loging,String password) throws MessagingException;

    }
