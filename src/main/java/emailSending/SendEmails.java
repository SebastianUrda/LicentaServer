package emailSending;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.List;
import java.util.Properties;

public class SendEmails {
    private static final Logger LOGGER = LogManager.getLogger(SendEmails.class);

    public void send(List<String> mailingList, String subject, String body) {
        final String fromEmail = "homealertscs@gmail.com"; //requires valid gmail id
        final String password = "admin0105"; // correct password for gmail id

        LOGGER.info("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        LOGGER.info("Session created");
        for (String receiver : mailingList) {
            EmailUtil.sendEmail(session, receiver, subject, body);
        }

    }
}
