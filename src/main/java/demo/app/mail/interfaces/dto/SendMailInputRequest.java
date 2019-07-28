package demo.app.mail.interfaces.dto;

import com.amazonaws.services.simpleemail.model.*;
import demo.app.mail.domain.MailType;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * Created by itaesu on 28/07/2019.
 */
@Getter @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class SendMailInputRequest {
    private List<String> recipients;
    private String title;
    private String content;
    private MailType mailType;
    private Map<String, String> model;

    public Map<String, String> getModel() {
        return CollectionUtils.isEmpty(model)
                ? Collections.emptyMap()
                : model;
    }

    public String getTemplatePath() {
        return format(mailType.getLocation(), getLocale()).toLowerCase();
    }

    public SendEmailRequest toSendRequestDto(){
        Destination destination = new Destination()
                .withToAddresses(recipients);

        Message message = new Message()
                .withSubject(createContent(this.title))
                .withBody(new Body().withHtml(createContent(this.content)));

        return new SendEmailRequest()
                .withSource("taesu@crscube.co.kr")
                .withDestination(destination)
                .withMessage(message);
    }

    private Content createContent(String text) {
        return new Content()
                .withCharset("UTF-8")
                .withData(text);
    }

    public SendMailInputRequest attachContent(String content) {
        this.content = content;
        return this;
    }
}
