package hotel.web.admin;

import hotel.data.HotelRepository;
import hotel.data.RoomRepository;
import hotel.entities.Hotel;
import hotel.entities.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/hotel")
@AllArgsConstructor
public class HotelMngController {
    private final HotelRepository hotelRepo;
    private final RoomRepository roomRepo;

    @GetMapping
    public String hotelForm(Model model) {
        List<Hotel> lstHotel = hotelRepo.findAll();
        model.addAttribute("hotels", lstHotel);
        return "admin/hotelmng";
    }

    @GetMapping("/add_hotel")
    public String addHotel() {
        return "admin/addhotel";
    }

    @PostMapping("/added_hotel")
    public String addedHotel(@RequestParam(name="name") String name,
                             @RequestParam(name="address") String address,
                             @RequestParam(name="starLevel") Integer starLevel,
                             @RequestParam(name="description") String description) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setStarLevel(starLevel);
        hotel.setDescription(description);
        hotelRepo.save(hotel);
        return "redirect:/admin/hotel";
    }

    @GetMapping("/delhotel")
    public String delhotel(@RequestParam(name="hotel_id") Long id, Model model) {
        Optional<Hotel> hotelEntity = hotelRepo.findById(id);
        Hotel hotel = hotelEntity.get();
        model.addAttribute("hotel", hotel);
        return "admin/delHotel";
    }

    @PostMapping("/deledhotel")
    public String deledhotel(@RequestParam(name="id") Long id) {
        Optional<Hotel> hotelEntity = hotelRepo.findById(id);
        Hotel hotel = hotelEntity.get();
        hotelRepo.delete(hotel);
        return "redirect:/admin/hotel";
    }

    @GetMapping("/edithotel")
    public String edithotel(@RequestParam(name="hotel_id") Long id, Model model) {
        Optional<Hotel> hotelEntity = hotelRepo.findById(id);
        Hotel hotel = hotelEntity.get();
        model.addAttribute("hotel", hotel);
        return "admin/delHotel";
    }

    @PostMapping("/editedhotel")
    public String editedhotel(@RequestParam(name="id") Long id,
                              @RequestParam(name="name") String name,
                              @RequestParam(name="address") String address,
                              @RequestParam(name="starLevel") Integer starLevel,
                              @RequestParam(name="description") String description) {
        hotelRepo.updateHotel(id, name, address, starLevel, description);
        return "redirect:/admin/hotel";
    }

    @GetMapping("/room")
    public String roomForm(@RequestParam(name="hotel_id") Long id, Model model) {
        model.addAttribute("id", id);
        List<Room> lstRoom = roomRepo.findByHotelId(id);
        model.addAttribute("rooms", lstRoom);
        return "admin/roommng";
    }

    @GetMapping("/addRoom")
    public String addRoom() {
        return "admin/addRoom";
    }

    @PostMapping("/addedRoom")
    public String addedRoom(@RequestParam(name="name") String name,
                            @RequestParam(name="type") String type,
                            @RequestParam(name="price") Long price,
                            @RequestParam(name="status") String status,
                            @RequestParam(name="description") String description) {
        return "redirect:/admin/hotel/room";
    }

}
