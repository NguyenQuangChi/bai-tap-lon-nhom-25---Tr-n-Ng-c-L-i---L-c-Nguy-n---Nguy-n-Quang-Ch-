package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import hotel.entities.BookedRoom;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
