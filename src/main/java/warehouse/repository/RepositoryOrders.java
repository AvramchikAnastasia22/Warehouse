package warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Orders;
@Repository
public interface RepositoryOrders extends JpaRepository<Orders, Integer> {
}
