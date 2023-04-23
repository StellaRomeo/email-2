package co.develhope.email2.emails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendTo(String email, String title, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<h1>Hello World!</h1>" +
                "<h2>You have a new message: </h2>" +
                "<img src='https://w7.pngwing.com/pngs/408/109/png-transparent-heart-illustration-heart-romance-love-cuore-love-heart-wikimedia-commons-thumbnail.png' " +
                "alt='Alternative text' height='200'>" +
                "<h3>" + text + "</h3>";        // ho cambiato link immagine per provare
        helper.setText(htmlMsg, true);
        helper.setTo(email);
        helper.setSubject(title);
        helper.setFrom(sender);
        javaMailSender.send(mimeMessage);
    }
}
