package hotel.web.admin;

import hotel.data.UserRepository;
import hotel.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController {
    private final UserRepository userRepo;

    @GetMapping("/admin")
    public String loginForm() {
        return "admin/loginmng";
    }

    @GetMapping("/admin/home")
    public String home() {
        return "admin/homemng";
    }

    @PostMapping("/admin/process_login")
    public String processLogin(@RequestParam(name="username") String username,
                               @RequestParam(name="password") String password) {
        User user = userRepo.findByUsername(username);
        if(user == null || !password.equals(user.getPassword()) || !user.getPosition().equals("admin"))
            return "redirect:/admin";
        return "redirect:/admin/home";
    }
}
