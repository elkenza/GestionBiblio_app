package ma.xproce.gestionbiblio.Security.controllers;

import ma.xproce.gestionbiblio.Security.entities.User;
import ma.xproce.gestionbiblio.Security.repositories.UserRepository;
import ma.xproce.gestionbiblio.Security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
    @Autowired
    AccountService accountService;

    @GetMapping("/indexpage")
    public String home(){

        return "redirect:books";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
    @GetMapping("register")
    public String register(Model model){
        // model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("submit_register")
    public String submit_register(@RequestParam("userName")String username,
                                  @RequestParam("email")String email,
                                  @RequestParam("password")String password,
                                  @RequestParam("confirm") String confirm){
        accountService.createUser(username,email,password,confirm);
        System.out.println(username);

        accountService.addRoleToUser(username,"USER");
        System.out.println(username);
        return "redirect:login";
    }



}
