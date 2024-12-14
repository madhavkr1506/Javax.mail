package winter.textmenow;

import javax.mail.*;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReplyToEmail {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        String to = "madhavkr9153276724@gmail.com";
        String password = "yfprtdjdtwmpzini";
        String from = "madhavkr9153276724@gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Reply Message");
            message.setContent("<h1 style='color:red'>This is Madhav from LPU</h1>","text/html");
            message.setReplyTo(InternetAddress.parse("ramjeeald10@gmail.com"));

            Transport.send(message);

            System.out.println("madhavkr9153276724@gmail.com");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
