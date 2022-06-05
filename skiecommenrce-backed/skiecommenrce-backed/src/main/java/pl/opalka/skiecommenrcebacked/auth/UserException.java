package pl.opalka.skiecommenrcebacked.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserException extends RuntimeException {

    String message;

    public UserException(String message) {
        super(message);
    }
}
