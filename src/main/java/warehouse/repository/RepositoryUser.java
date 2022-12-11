package warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.model.User;
@Repository
public interface RepositoryUser extends JpaRepository<User, Integer> {
}
