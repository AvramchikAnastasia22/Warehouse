package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.History;
@Repository
public interface RepositoryHistory extends JpaRepository<History, Integer> {
}
