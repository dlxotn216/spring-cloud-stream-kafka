package demo.app.greeting.listener;

import demo.app.greeting.domain.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * Created by itaesu on 28/07/2019.
 */
@EnableBinding(GreetingStream.class)
@Slf4j
public class GreetingListener {
    @StreamListener(GreetingStream.INPUT)
    public void handle(@Payload Greeting greeting) {
        log.info("Received greeting: {}", greeting);
    }
}
