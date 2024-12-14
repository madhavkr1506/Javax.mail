package winter.textmenow;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class InlineImage {
    public static void main(String[] args) {
        String to = "madhavkr9153276724@gmail.com";
        String from = "madhavkr9153276724@gmail.com";
        String password = "yfprtdjdtwmpzini";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{

            Message message  = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Inline Image");
            
            MimeBodyPart textbodypart = new MimeBodyPart();
            String text = "<p style='color:red'>This is inline image text</p>" + "<img src='cid:image1'>";
            textbodypart.setContent(text,"text/html");

            MimeBodyPart imagepart = new MimeBodyPart();
            String imagepath = "C:\\Users\\madha\\Pictures\\ai.png";

            DataSource imagefile = new FileDataSource(imagepath);
            imagepart.setDataHandler(new DataHandler(imagefile));
            imagepart.setHeader("Content-id", "<image1>");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textbodypart);
            multipart.addBodyPart(imagepart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Message sent successfully");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
