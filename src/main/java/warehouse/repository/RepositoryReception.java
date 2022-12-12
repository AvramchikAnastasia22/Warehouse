package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.Reception;

@Repository
public interface RepositoryReception extends JpaRepository<Reception, Integer> {
}
