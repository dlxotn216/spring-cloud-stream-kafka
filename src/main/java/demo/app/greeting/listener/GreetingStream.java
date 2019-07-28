package demo.app.greeting.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by itaesu on 28/07/2019.
 */
public interface GreetingStream {
    String INPUT = "greeting-in";
    String OUTPUT = "greeting-out";

    @Input(INPUT)
    SubscribableChannel inboudGreeting();

    @Output(OUTPUT)
    MessageChannel outputGreeting();
}
