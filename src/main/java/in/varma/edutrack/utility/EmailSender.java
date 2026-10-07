package in.varma.edutrack.utility;

import in.varma.edutrack.exception.EmailSendingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailSender {

    private JavaMailSender mailSender;

    public void sendEmail(String subject, String body, String to) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage);

            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setTo(to);

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailSendingException(
                    "Failed to send email to: " + to, e
            );
        }
    }
}