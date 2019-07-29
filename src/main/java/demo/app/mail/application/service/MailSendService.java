package demo.app.mail.application.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import demo.app.mail.interfaces.dto.SendMailInputRequest;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by itaesu on 28/07/2019.
 * @author Lee Tae Su
 */
@Slf4j
@Service @RequiredArgsConstructor
public class MailSendService {

    private final FreeMarkerConfig freemarkerConfig;

    @Async
    public CompletableFuture<SendEmailResult> send(SendMailInputRequest mail) {
        String content = getContent(mail);
        mail.attachContent(content);
        if (log.isDebugEnabled()) {
            log.debug("Parsed mail body is {}", content);
        }

        try {
            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                                                                                   .withCredentials(credentialsProvider)
                                                                                   .withRegion("us-west-2")
                                                                                   .build();

            return CompletableFuture.completedFuture(client.sendEmail(mail.toSendRequestDto()));
        } catch (Exception ex) {
            throw new AmazonClientException(ex.getMessage(), ex);
        }
    }

    private String getContent(SendMailInputRequest mail) {
        try {
            final Template template = freemarkerConfig.getConfiguration().getTemplate(mail.getTemplatePath());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        } catch (IOException | TemplateException e) {
            log.error("Cannot load template {}", mail.getTemplatePath(), e);
            throw new IllegalArgumentException(e);
        }
    }
}
