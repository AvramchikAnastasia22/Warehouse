package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Supplier;
@Repository
public interface RepositorySupplier extends JpaRepository<Supplier,Integer> {
}
