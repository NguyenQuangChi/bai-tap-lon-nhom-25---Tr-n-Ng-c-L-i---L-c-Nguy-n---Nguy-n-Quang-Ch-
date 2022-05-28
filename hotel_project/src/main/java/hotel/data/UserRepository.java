package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import hotel.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
