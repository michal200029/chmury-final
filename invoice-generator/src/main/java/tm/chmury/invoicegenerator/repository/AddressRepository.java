package tm.chmury.invoicegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.chmury.invoicegenerator.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
