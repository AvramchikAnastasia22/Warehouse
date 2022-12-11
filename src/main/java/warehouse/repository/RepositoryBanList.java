package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.BanList;
@Repository
public interface RepositoryBanList extends JpaRepository<BanList, Integer> {
}
