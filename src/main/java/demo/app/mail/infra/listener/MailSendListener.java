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
 * @author Lee Tae Su
 */
@Slf4j
@EnableBinding(MailSendStream.class)
@RequiredArgsConstructor
public class MailSendListener {
    private final MailSendService mailSendService;

    @StreamListener(MailSendStream.INPUT)
    public void handleSendMail(Message<SendMailInputRequest> message) {
        final Acknowledgment acknowledgment
                = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

        //max poll interval ms 설정에 의해 오래 걸리는 작업이 있는 경우
        //다른 Consumer에 할당되어 중복 처리될 가능성이 있음
        this.mailSendService.send(message.getPayload())
                            .thenAccept(sendEmailResult -> {
                                log.info("Send mail result message id [{}]", sendEmailResult.getMessageId());
                                log.info("Send mail result response {}", sendEmailResult.getSdkResponseMetadata());
                                if (acknowledgment != null) {
                                    acknowledgment.acknowledge();      //Auto commit을 하지 않ㅇ고 명시적으로 커밋 함
                                }
                            })
                            .exceptionally(throwable -> {
                                log.error("The email was not sent. [{}]", throwable.getMessage());
                                log.error("Exception is ", throwable);
                                return null;
                            });
    }
}
