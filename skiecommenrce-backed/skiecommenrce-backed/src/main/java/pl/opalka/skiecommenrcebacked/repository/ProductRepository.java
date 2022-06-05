package pl.opalka.skiecommenrcebacked.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import pl.opalka.skiecommenrcebacked.model.Product;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM Product p WHERE p.units_in_stock > 0 AND p.category_id = :id", nativeQuery = true)
    List<Product> findAllAvailableByCategoryId(@RequestParam("id") Long id);

}