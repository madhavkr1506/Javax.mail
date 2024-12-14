package winter.textmenow;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailWithAttachment {
    public static void main(String[] args) {
        String to =  "madhavkr9153276724@gmail.com";
        String from = "madhavkr9153276724@gmail.com";
        String password = "yfprtdjdtwmpzini";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Multimedia message");

            MimeBodyPart textpart = new MimeBodyPart();
            textpart.setText("Hello this is text part");

            MimeBodyPart attachmentpart = new MimeBodyPart();
            String attachFile = "C:\\Users\\madha\\Pictures\\ai.png";
            attachmentpart.attachFile(new File(attachFile));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textpart);
            multipart.addBodyPart(attachmentpart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Message has been sent successfully with attachment");


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
