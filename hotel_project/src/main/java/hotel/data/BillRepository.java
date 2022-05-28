package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import hotel.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
