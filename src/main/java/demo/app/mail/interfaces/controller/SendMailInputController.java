package demo.app.mail.interfaces.controller;

import demo.app.mail.application.service.SendMailInputService;
import demo.app.mail.interfaces.dto.SendMailInputRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by itaesu on 28/07/2019.
 */
@RestController @RequiredArgsConstructor
public class SendMailInputController {
    private final SendMailInputService sendMailInputService;

    @PostMapping("/mails")
    public void handleSendMailInputRequest(@RequestBody SendMailInputRequest request) {
        this.sendMailInputService.sendMailInput(request);
    }
}
