package erecrutement.finances.gov.ma.MEF.Services;

public interface IEmailSending {
    public String sendEmail(String email,String subject,String text);
    public String sendEmailwithAttachment(String email,String subject,String text,String filepath);
}
