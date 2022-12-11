package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Product;

@Repository
public interface RepositoryProduct extends JpaRepository<Product, Integer> {
}
