package demo.app.mail.infra.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by itaesu on 28/07/2019.
 */
public interface MailSendStream {
    String INPUT = "send-mail-in";
    String OUTPUT = "send-mail-out";

    @Input(INPUT)
    SubscribableChannel inputSendMail();

    @Output(OUTPUT)
    MessageChannel outputSendMail();
}
