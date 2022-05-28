package hotel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import hotel.entities.Hotel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Modifying
    @Transactional
    @Query(value="update Hotel h set h.name = :name, h.address = :address, h.starLevel = :starLevel, h.description = :description where h.id = :id")
    void updateHotel(@Param("id") Long id, @Param("name") String name, @Param("address") String address, @Param("starLevel") Integer starLevel, @Param("description") String description);
}
