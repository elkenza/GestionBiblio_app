package ma.xproce.gestionbiblio.web;

import ma.xproce.gestionbiblio.dao.entities.Auteur;
import ma.xproce.gestionbiblio.dao.entities.Book;
import ma.xproce.gestionbiblio.service.AuteurManager;
import ma.xproce.gestionbiblio.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class AuteurController {
    @Autowired
    AuteurManager auteurManager;
    @Autowired
    BookManager bookManager;

    @GetMapping("/listeAuteurs")
    public String listeAuteurs(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "taille",defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Auteur> auteurList=auteurManager.searchAuteur(keyword,page,taille);
        model.addAttribute("listeAuteurs",auteurList.getContent());
        int[] pages=new int[auteurList.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage",page);
        return "listeAuteurs";
    }
    @GetMapping("/deleteAuteur")
    public String deleteAuteur(Model model,@RequestParam(name = "id") Integer id){
        if(auteurManager.deleteAuteurById(id))
            return "redirect:listeAuteurs";
        else
            return "error";
    }
    @GetMapping("ajouterAuteur")
    public String add(){
        return "ajouterAuteur";
    }
    @PostMapping("ajouterAuteur")
    public String addAuteur( @RequestParam(name = "name") String name,
                            @RequestParam(name = "dateNaissance") LocalDateTime dateNaissance
                            ){
        Auteur auteur=new Auteur(null,name,Date.from(dateNaissance.atZone(ZoneId.systemDefault()).toInstant()),null);
        auteurManager.addAuteur(auteur);
        return "redirect:listeAuteurs";

    }

    @GetMapping("/editAuteur")
    public String editAuteur(Model model,@RequestParam(name = "id") Integer id){
        Auteur auteur=auteurManager.getAuteurById(id);
        model.addAttribute("updatedAuteur",auteur);
        return "editAuteur";
    }
    @PostMapping("/modifierAuteur")
    public String modifier( @RequestParam(name = "id") Integer id,
                            @RequestParam(name = "name") String name,
                           @RequestParam(name = "dateNaissance") LocalDateTime dateNaissance,
                           @RequestParam(name = "books") String books){

        Auteur auteur=new Auteur(id,name,Date.from(dateNaissance.atZone(ZoneId.systemDefault()).toInstant()),null);
        auteurManager.updateAuteur(auteur);
        return "redirect:listeAuteurs";
    }
    @GetMapping("ajouterLivrePourAuteur")
    public String ajouterLivrePourAuteur(Model model,@RequestParam(name = "id") Integer id){
        Collection<Auteur> auteurs=new ArrayList<>();
        Auteur auteur=auteurManager.getAuteurById(id);
        auteurs.add(auteur);
        Book book=new Book();
        book.setAuteurs(auteurs);
        model.addAttribute("aut",auteur);
        model.addAttribute("book",book);

        return "ajouterLivrePourAuteur";
    }
    @PostMapping("ajouterLivrePourAuteur")
    public String addrLivrePourAuteur(@RequestParam(name = "Title") String title,
                                      @RequestParam(name = "nbPages") Integer nbPages,
                                      @RequestParam(name = "auteurs") String  auteur){

        Collection<Auteur> auteurs=new ArrayList<>();
        Auteur aut=auteurManager.getAuteurByName(auteur);
        Collection<Book> books=new ArrayList<>();
        auteurs.add(aut);
        Book b1=bookManager.getBookByTitle(title);
        if (b1 != null){
            books.add(b1);
            aut.setBooks(books);
            auteurManager.updateAuteur(aut);
            return "redirect:books";
        }else{

        Book book=new Book(null,title,nbPages,null,auteurs,null,null);
        bookManager.addBook(book);
        books.add(book);
        aut.setBooks(books);
        auteurManager.updateAuteur(aut);
        return "redirect:books";}
    }
}
