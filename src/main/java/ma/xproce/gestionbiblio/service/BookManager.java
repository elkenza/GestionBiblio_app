package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Book;
import org.springframework.data.domain.Page;


import java.util.List;

public interface BookManager {
    public Book addBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(Book book);
    public boolean deleteBookById(Integer id);
    public List<Book> getAllBooks();
    public Book getBookById(Integer id);
    public Page<Book> searchBook(String keyword,int page,int taille);
    public Book getBookByTitle(String title);

}
