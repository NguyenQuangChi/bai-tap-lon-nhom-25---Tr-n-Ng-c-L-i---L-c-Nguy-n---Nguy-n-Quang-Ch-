package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import hotel.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
