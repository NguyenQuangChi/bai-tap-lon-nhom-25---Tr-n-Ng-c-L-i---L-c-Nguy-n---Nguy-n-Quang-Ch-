package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import hotel.entities.Room;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Modifying
    @Transactional
    @Query(value="update Room r set r.status = :status where r.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    List<Room> findByHotelId(Long hotelId);
}
