package hotel.web.customer;

import hotel.data.RoomRepository;
import hotel.entities.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import hotel.entities.BookedRoom;
import hotel.data.BookedRoomRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping
@AllArgsConstructor
public class BookingController {
    private final RoomRepository roomRepo;
    private final BookedRoomRepository bookedRepo;

    @GetMapping("/booking")
    public String bookingForm(@RequestParam Long id, Model model) {
        Optional<Room> roomEntity = roomRepo.findById(id);
        Room room = roomEntity.get();
        model.addAttribute("room", room);
        model.addAttribute("id", id);
        return "customer/booking";
    }

    @PostMapping("/booked")
    public String booked(@RequestParam(name="roomid") Long id,
                         @RequestParam(name="checkin") String checkin,
                         @RequestParam(name="checkout") String checkout) throws ParseException {
        BookedRoom bkroom = new BookedRoom();
        Optional<Room> roomEntity = roomRepo.findById(id);
        Room room = roomEntity.get();
        bkroom.setRoom(room);
        bkroom.setPrice(room.getPrice());
        Date in = new SimpleDateFormat("yyyy-MM-dd").parse(checkin);
        Date out = new SimpleDateFormat("yyyy-MM-dd").parse(checkout);
        bkroom.setCheckin(in);
        bkroom.setCheckout(out);
        bkroom.setIsCheckin(1);
        bookedRepo.save(bkroom);
        roomRepo.updateStatus(room.getId(), "Yes");
        return "customer/booking_success";
    }
}
