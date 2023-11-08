package leejaewoo.server.book_category.entity;

import leejaewoo.server.book.entity.Book;
import leejaewoo.server.category.entity.Category;
import leejaewoo.server.global.audit.Auditable;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BookCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCategoryId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
