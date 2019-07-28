package demo.app.mail.infra.listener;

import demo.app.mail.application.service.MailSendService;
import demo.app.mail.infra.stream.MailSendStream;
import demo.app.mail.interfaces.dto.SendMailInputRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

/**
 * Created by itaesu on 28/07/2019.
 */
@Slf4j
@EnableBinding(MailSendStream.class)
@RequiredArgsConstructor
public class MailSendListener {
    private final MailSendService mailSendService;

    @StreamListener(MailSendStream.INPUT)
    public void handleSendMail(Message<SendMailInputRequest> message) {
        this.mailSendService.send(message.getPayload());

        final Acknowledgment acknowledgment
                = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

        if(acknowledgment != null){
            acknowledgment.acknowledge();      //Auto commit을 하지 않ㅇ고 명시적으로 커밋 함
        }
    }
}
