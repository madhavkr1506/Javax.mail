package winter.textmenow;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class MailSender {

    public static void main(String[] args) {
        String to = "worldalpha870@gmail.com";
        String host = "smtp.gmail.com";
        String from = "madhavkr9153276724@gmail.com";
        String password = "yfprtdjdtwmpzini";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Hello Message");
            message.setText("Hello this message find you well");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        }catch(MessagingException e){
            e.printStackTrace();
        }

    }
}