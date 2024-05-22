package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.entities.Book;
import ma.xproce.gestionbiblio.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookManagerService implements BookManager{
    @Autowired
    BookRepository bookRepository;
    @Override
    public Book addBook(Book book) {
        try{
           return bookRepository.save(book);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }

    }

    @Override
    public Book updateBook(Book book) {
        Book bk= bookRepository.findById(book.getIdBook()).get();
        bk.setAdherents(book.getAdherents());
        bk.setEmprunts(book.getEmprunts());
        bk.setTitle(book.getTitle());
        bk.setNbPages(book.getNbPages());
        bk.setAuteurs(book.getAuteurs());
        bk.setImage(book.getImage());
        bookRepository.save(bk);

        return bk;
    }

    @Override
    public boolean deleteBook(Book book) {
        if(bookRepository.existsById(book.getIdBook())) {
            bookRepository.delete(book);
            return true;
        }else
            return false;

    }

    @Override
    public boolean deleteBookById(Integer id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Page<Book> searchBook(String keyword, int page, int taille) {
        return bookRepository.findByTitleContains(keyword,PageRequest.of(page,taille));
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }


}
