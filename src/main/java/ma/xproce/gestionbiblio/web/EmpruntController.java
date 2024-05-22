package ma.xproce.gestionbiblio.web;

import ma.xproce.gestionbiblio.Security.entities.User;
import ma.xproce.gestionbiblio.Security.service.AccountService;
import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.entities.Book;
import ma.xproce.gestionbiblio.dao.entities.Emprunt;
import ma.xproce.gestionbiblio.service.AdherentManager;
import ma.xproce.gestionbiblio.service.BookManager;
import ma.xproce.gestionbiblio.service.EmpruntManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class EmpruntController {
    @Autowired
    EmpruntManager empruntManager;
    @Autowired
    BookManager bookManager;
    @Autowired
    AdherentManager adherentManager;
    @Autowired
    AccountService accountService;
   @GetMapping("listEmprunts")
   public String listEmprunts(Model model){
       List<Emprunt> emprunts=empruntManager.getAllEmprunts();
       model.addAttribute("emprunts",emprunts);


       return "listEmprunts";
   }
    @GetMapping("addEmprunt")
    public String addEmprunt(@RequestParam("bookId")Integer id,@RequestParam("username")String name,Model model){
        Book book=bookManager.getBookById(id);
        LocalDate today= LocalDate.now();
        User user=accountService.loadUserByUsername(name);
        Adherent adherent1=adherentManager.getAdherentByName(name);
        Emprunt emprunt;
        if (adherent1 != null)
        { emprunt=new Emprunt(null,today,today.plusMonths(1),book,adherent1);
        }else {
        if (user==null)throw new RuntimeException("User unfound");
        Adherent adherent=new Adherent(null,user.getUserName(),null,null,null,null);

        emprunt=new Emprunt(null,today,today.plusMonths(1),book,adherent);
        adherentManager.addAdherent(adherent);}
        empruntManager.addEmprunt(emprunt);
        model.addAttribute("profile",emprunt);
        return "profile";
    }
    @GetMapping("deleteEmprunt")
    public String deleteEmprunt(@RequestParam(name = "id") Integer id){
       empruntManager.deleteEmpruntById(id);
       return "redirect:listEmprunts";
    }


}
