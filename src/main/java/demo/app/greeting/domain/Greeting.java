package demo.app.greeting.domain;

import lombok.*;

/**
 * Created by itaesu on 28/07/2019.
 */
@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class Greeting {
    private Long timestamp;
    private String message;
}
