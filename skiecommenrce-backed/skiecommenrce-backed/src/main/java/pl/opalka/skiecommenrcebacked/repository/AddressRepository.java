package pl.opalka.skiecommenrcebacked.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.opalka.skiecommenrcebacked.model.Address;

@Repository
@CrossOrigin("http://localhost:4200")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
