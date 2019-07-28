package demo.app.mail.application.service;

import demo.app.mail.infra.stream.MailSendStream;
import demo.app.mail.interfaces.dto.SendMailInputRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;
import static org.springframework.messaging.support.MessageBuilder.withPayload;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

/**
 * Created by itaesu on 28/07/2019.
 */
@Slf4j
@Service @RequiredArgsConstructor
public class SendMailInputService {
    private final MailSendStream mailSendStream;

    public void sendMailInput(SendMailInputRequest request) {
        final Message<SendMailInputRequest> build
                = withPayload(request).setHeader(CONTENT_TYPE, APPLICATION_JSON).build();

        final boolean send = this.mailSendStream.outputSendMail().send(build);
        if (!send) {
            log.error("Mail publish fail {}", request);
        }
    }
}
