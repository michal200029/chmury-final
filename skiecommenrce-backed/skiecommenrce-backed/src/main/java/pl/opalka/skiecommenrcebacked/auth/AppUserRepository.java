package pl.opalka.skiecommenrcebacked.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.opalka.skiecommenrcebacked.model.AppUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, UUID> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

}