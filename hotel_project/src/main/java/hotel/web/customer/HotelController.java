package hotel.web.customer;

import hotel.data.HotelRepository;
import hotel.data.RoomRepository;
import hotel.entities.Hotel;
import hotel.entities.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController {
    private final HotelRepository hotelRepo;
    private final RoomRepository roomRepo;

    @GetMapping
    public String hotelForm(Model model) {
        List<Hotel> lstHotel = hotelRepo.findAll();
        model.addAttribute("hotels", lstHotel);
        return "customer/hotel";
    }

    @GetMapping("/room")
    public String roomForm(@RequestParam(name="hotel_id") Long id, Model model) {
        model.addAttribute("id", id);
        List<Room> lstRoom = roomRepo.findByHotelId(id);
        model.addAttribute("rooms", lstRoom);
        return "customer/room";
    }

    @GetMapping("/search")
    public String searchHotel(@PathVariable(name="key") String key, Model model) {
        model.addAttribute("key", key);
        key = key.toLowerCase();
        List<Hotel> lstHotel = hotelRepo.findAll();
        List<Hotel> hotels = new ArrayList<>();
        for(Hotel h : lstHotel) {
            String name = h.getName().toLowerCase();
            if(name.contains(key)) {
                hotels.add(h);
            }
        }
        model.addAttribute("hotels", hotels);
        return "customer/search";
    }
}
