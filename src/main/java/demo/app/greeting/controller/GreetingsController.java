package demo.app.greeting.controller;

import demo.app.greeting.domain.Greeting;
import demo.app.greeting.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by itaesu on 28/07/2019.
 */

@RestController @RequiredArgsConstructor
public class GreetingsController {
    private final GreetingService greetingsService;

    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("message") String message) {
        Greeting greetings = Greeting.builder()
                                     .message(message)
                                     .timestamp(System.currentTimeMillis())
                                     .build();
        greetingsService.sendGreeting(greetings);
    }
}
