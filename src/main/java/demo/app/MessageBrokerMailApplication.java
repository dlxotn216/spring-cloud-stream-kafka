package demo.app;

import demo.app.greeting.listener.GreetingStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(GreetingStream.class)
public class MessageBrokerMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageBrokerMailApplication.class, args);
	}

}
