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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookManager bookManager;
    @Autowired
    AuteurManager auteurManager;
    @GetMapping("/test")
    public String test(Model model){
        List<Book> books=bookManager.getAllBooks();
        model.addAttribute("books",books);
        return "test";
    }

    @GetMapping("/books")
    public String listBooks(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "taille",defaultValue = "6") int taille,
                            @RequestParam(name = "search", defaultValue = "") String keyword){
    Page<Book> bookList=bookManager.searchBook(keyword,page,taille);
    model.addAttribute("listeLivres",bookList.getContent());
    int[] pages=new int[bookList.getTotalPages()];
    model.addAttribute("pages",pages);
    model.addAttribute("keyword", keyword);
    model.addAttribute("currentPage",page);
    return "listBooks";
    }
    @GetMapping("/Ajouter")
    public String ajouterBook(Model model){
        model.addAttribute("book",new Book());
        return "ajouterBook";
    }
    @PostMapping("/ajout")
    public String ajouterBook(Model model,
                              @RequestParam(name = "Title") String title,
                              @RequestParam(name = "nbPages") Integer nbPages,
                              @RequestParam(name = "auteurs") String auteur,
                              @RequestParam(name = "adherents") String adherents,
                              @RequestParam(name = "auteurs") String emprunts,
                              @RequestParam(name="image") String image)
    {
        Book book=new Book(null,title,nbPages,image,null,null,null);
        Collection<Auteur> auteurs=new ArrayList<>();
        Collection<Auteur> auteurs1=new ArrayList<>();
        //auteurs.add(auteur);
        Auteur auteur1=auteurManager.getAuteurByName(auteur);
        Collection<Book> books=new ArrayList<>();
        if (auteur1 == null ){

            books.add(book);
            Auteur aut=new Auteur(null,auteur1.getName(),null,books);
            auteurs1.add(aut);
            book.setAuteurs(auteurs1);
            auteurManager.addAuteur(aut);
        }else{
            auteurs.add(auteur1);
            book.setAuteurs(auteurs);
        }

       // Book book=new Book(null,title,nbPages,image,null,null,null);
        bookManager.addBook(book);
        return "redirect:books";
    }
    @GetMapping("/deleteBook")
    public String deleteBook(Model model,@RequestParam(name = "id") Integer id){
        if(bookManager.deleteBookById(id))
            return "redirect:books";
        else
            return "error";
    }
    @GetMapping("/editBook")
    public String editBook(Model model,@RequestParam(name = "id") Integer id){
        Book book=bookManager.getBookById(id);
        model.addAttribute("bookUpdated",book);
        return "editBook";
    }
    @PostMapping("/edit")
    public String updateBooks(Model model,@RequestParam(name = "Title") String title,
                              @RequestParam(name = "nbPages") Integer nbPages,
                              @RequestParam(name = "id") Integer id,
                              @RequestParam(name = "auteurs") String auteur,
                              @RequestParam(name = "adherents") String adherents,
                              @RequestParam(name = "auteurs") String emprunts,
                              @RequestParam(name="image") String image){

        Book book=new Book(id,title,nbPages,image,null,null,null);
        Collection<Auteur> auteurs=new ArrayList<>();
        Collection<Auteur> auteurs1=new ArrayList<>();
        Auteur auteur1=auteurManager.getAuteurByName(auteur);
        Collection<Book> books=new ArrayList<>();
        if (auteur1 == null ){

            books.add(book);
            Auteur aut=new Auteur(null,auteur1.getName(),null,books);
            auteurs1.add(aut);
            book.setAuteurs(auteurs1);
            auteurManager.addAuteur(aut);
        }else{
            auteurs.add(auteur1);
            book.setAuteurs(auteurs);
        }

        // Book book=new Book(null,title,nbPages,image,null,null,null);
        bookManager.updateBook(book);
        return "redirect:books";
    }


}
