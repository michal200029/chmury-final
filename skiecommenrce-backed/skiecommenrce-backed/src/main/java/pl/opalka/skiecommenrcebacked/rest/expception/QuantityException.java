package pl.opalka.skiecommenrcebacked.rest.expception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class QuantityException extends RuntimeException {

    String message;

    public QuantityException(String message) {
        super(message);
        this.message = message;
    }
}
