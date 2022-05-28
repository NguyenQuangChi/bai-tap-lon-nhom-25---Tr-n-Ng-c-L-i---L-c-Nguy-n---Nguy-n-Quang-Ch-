package hotel.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkin;
    private Date checkout;
    private Long price;
    private Integer isCheckin;
    @ManyToOne()
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;
}