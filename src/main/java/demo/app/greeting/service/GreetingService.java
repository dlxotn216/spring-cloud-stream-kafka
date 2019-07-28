package demo.app.greeting.service;

import demo.app.greeting.domain.Greeting;
import demo.app.greeting.listener.GreetingStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;
import static org.springframework.messaging.support.MessageBuilder.withPayload;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

/**
 * Created by itaesu on 28/07/2019.
 */
@Slf4j
@Service @RequiredArgsConstructor
public class GreetingService {
    private final GreetingStream greetingStream;

    public void sendGreeting(Greeting greeting) {
        this.greetingStream.outputGreeting()
                           .send(withPayload(greeting).setHeader(CONTENT_TYPE, APPLICATION_JSON).build());
    }
}
