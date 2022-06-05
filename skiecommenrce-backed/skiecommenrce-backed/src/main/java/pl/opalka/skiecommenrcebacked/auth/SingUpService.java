package pl.opalka.skiecommenrcebacked.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.opalka.skiecommenrcebacked.model.AppUser;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SingUpService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UUID singUpUser(String username, String password, String email) {
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            throw new UserException("User with given username already exists");
        }

        optionalUser = appUserRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            throw new UserException("User with given email already exists");
        }

        password = bCryptPasswordEncoder.encode(password);
        UUID userId = UUID.randomUUID();
        AppUser appUser = new AppUser(userId, username, password, email);

        appUserRepository.save(appUser);

        return userId;
    }
}
