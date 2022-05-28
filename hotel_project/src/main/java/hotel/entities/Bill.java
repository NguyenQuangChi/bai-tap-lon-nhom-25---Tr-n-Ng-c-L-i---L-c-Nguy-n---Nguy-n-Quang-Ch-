package hotel.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date paymentDate;
    private String paymentType;
    private Long amount;
    private String note;
    @ManyToOne()
    @JoinColumn(name = "bookind_id")
    private Booking booking;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
