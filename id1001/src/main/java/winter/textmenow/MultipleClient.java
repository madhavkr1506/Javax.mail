package winter.textmenow;
import java.io.File;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class MultipleClient {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        String password = "yfprtdjdtwmpzini";
        String from = "madhavkr9153276724@gmail.com";

        String to = "ramjeeald10@gmail.com";
        String cc = "madhavkr9153276724@gmail.com";
        String bcc = "worldalpha870@gmail.com";

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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            // message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("madhavkr9153276724@gmail.com, ramjeeald10@gmail.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

            message.setSubject("Hello Message");
            // message.setContent("<h1 style='color:red'> Hello, This is Madhav from LPU</p>","text/html");

            MimeBodyPart textpart = new MimeBodyPart();
            String text = "<p style='color:red'>This is Madhav from LPU</p>";
            textpart.setContent(text,"text/html");

            MimeBodyPart imagepart = new MimeBodyPart();
            String imagepath = "C:\\Users\\madha\\Pictures\\ai.png";
            imagepart.attachFile(new File(imagepath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textpart);
            multipart.addBodyPart(imagepart);

            message.setContent(multipart);

            for(int i=0;i<10;i++){
                Transport.send(message);
                System.out.println("Message has been sent successfully");

            }

            // Transport.send(message);
            System.out.println("Message has been sent successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
