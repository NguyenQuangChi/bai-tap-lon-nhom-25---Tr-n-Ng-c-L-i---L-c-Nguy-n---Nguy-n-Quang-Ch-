package hotel.web.customer;

import hotel.data.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class RoomController {
    private final RoomRepository roomRepo;

}
