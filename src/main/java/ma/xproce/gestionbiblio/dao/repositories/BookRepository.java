package ma.xproce.gestionbiblio.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.gestionbiblio.dao.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findByTitleContains(String keyword, Pageable pageable);
    Book findBookByTitle(String title);

}
