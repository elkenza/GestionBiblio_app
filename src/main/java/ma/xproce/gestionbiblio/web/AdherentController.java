package ma.xproce.gestionbiblio.web;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.service.AdherentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdherentController {
    @Autowired
    AdherentManager adherentManager;
    @GetMapping("")
    public String index(){
        return "index";
    }
    @GetMapping("listeAdherents")
    public String listeAdherents(Model model,@RequestParam(name = "page",defaultValue = "0") int page,
                                 @RequestParam(name = "taille",defaultValue = "6") int taille,
                                 @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Adherent> listeAdherents=adherentManager.searchAdherents(keyword,page,taille);
        model.addAttribute("listeAdherents",listeAdherents.getContent());
        int[] pages=new int[listeAdherents.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage",page);
        return "listeAdherents";
    }
    @GetMapping("ajouterAdherent")
    public String ajouterAdherent(Model model){
        Adherent adherent=new Adherent();
        model.addAttribute("adherent",adherent);
        return "ajouterAdherent";
    }
    @PostMapping("ajouterAdherent")
    public String ajouterAdherent(@ModelAttribute Adherent adherent){
        Adherent ad=new Adherent(adherent.getIdAd(),adherent.getName(),adherent.getTele(),null,null,null);
        adherentManager.addAdherent(ad);
        return "redirect:listeAdherents";
    }
    @GetMapping("deleteAdherent")
    public String deleteAdherent(@RequestParam(name = "id") Integer id){
        if(adherentManager.deleteAdherentById(id))
            return "redirect:listeAdherents";
        else
            return "error";
    }
    @GetMapping("editAdherent")
    public String editAdherent(Model model,@RequestParam(name = "id") Integer id){
        Adherent adherent=adherentManager.getAdherentById(id);
        model.addAttribute("updatedAdherent",adherent);
        return "editAdherent";
    }
    @PostMapping("editAdherent")
    public String editAdherent(@RequestParam("id")Integer id,
                               @RequestParam("name")String name,
                               @RequestParam("tele")String tele){
        Adherent adherent=new Adherent(id,name,tele,null,null,null);
        adherentManager.updateAdherent(adherent);
        return "redirect:listeAdherents";
    }


}
