package pl.opalka.skiecommenrcebacked.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@AllArgsConstructor
public class AuthController {

    private final SingUpService singUpService;
    private final AppUserRepository appUserRepository;

    @PostMapping(value = "/auth/signup")
    public ResponseEntity signup(@RequestBody SingUpRequestBody body) {
        UUID userId = singUpService.singUpUser(body.getUsername(), body.getPassword(), body.getEmail());
        return ResponseEntity.ok(new UserRegisteredResponse(userId.toString()));
    }

    @Data
    @AllArgsConstructor
    class UserRegisteredResponse {
        String userId;
    }

    @Data
    static class SingUpRequestBody {
        String username;
        String password;
        String email;
    }
}
