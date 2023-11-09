package leejaewoo.server.category.entity;

import leejaewoo.server.book_category.entity.BookCategory;
import leejaewoo.server.global.audit.Auditable;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<BookCategory> bookCategories;
}
