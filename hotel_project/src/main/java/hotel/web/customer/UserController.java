package hotel.web.customer;

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
public class UserController {
    private final UserRepository userRepo;

    @GetMapping("/home")
    public String home() {
        return "customer/home";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "customer/register";
    }

    @PostMapping("process_register")
    public String processRegister(@RequestParam(name="username") String username,
                                  @RequestParam(name="password") String password,
                                  @RequestParam(name="fullname") String fullname,
                                  @RequestParam(name="address") String address,
                                  @RequestParam(name="tel") String tel,
                                  @RequestParam(name="email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setAddress(address);
        user.setTel(tel);
        user.setEmail(email);
        user.setPosition("customer");
        userRepo.save(user);
        return "customer/register_success";
    }

    @GetMapping("/")
    public String loginForm() {
        return "customer/login";
    }

    @PostMapping("/process_login")
    public String processLogin(@RequestParam(name="username") String username,
                               @RequestParam(name="password") String password) {
        User user = userRepo.findByUsername(username);
        if(user == null || !password.equals(user.getPassword()) || user.getPosition().equals("admin"))
            return "redirect:/";
        return "redirect:/home";
    }
}
