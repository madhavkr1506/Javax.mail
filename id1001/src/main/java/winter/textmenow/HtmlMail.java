package winter.textmenow;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

public class HtmlMail {
    public static void main(String[] args) {
        String to = "madhavkr9153276724@gmail.com";
        String from = "madhavkr9153276724@gmail.com";
        String password = "yfprtdjdtwmpzini";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
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
            message.setSubject("Html Message");
            String text = "<h1 style='color:red'> This is Html Message</h1>";
            message.setContent(text,"text/html");

            Transport.send(message);

            System.out.println("Message sent successfully");


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
