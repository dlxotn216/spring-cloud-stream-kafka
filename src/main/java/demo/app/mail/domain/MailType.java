package demo.app.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by itaesu on 28/07/2019.
 */
@AllArgsConstructor @Getter
public enum MailType {

    CTMS_USER_REGISTRATION("/ctms/user_registration/template_%s.ftl");

    private String location;
}
