package tm.chmury.invoicegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.chmury.invoicegenerator.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    OrderDetail findByOrderTrackingNumber(String orderTrackingNumber);
}
