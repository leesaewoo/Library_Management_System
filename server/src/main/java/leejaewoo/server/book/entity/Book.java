package leejaewoo.server.book.entity;

import leejaewoo.server.bookcategory.entity.BookCategory;
import leejaewoo.server.global.audit.Auditable;
import leejaewoo.server.rental.entity.Rental;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String name;

    private String publisher;

    private LocalDateTime publishedDate;

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookCategory> bookCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<Rental> rentals = new ArrayList<>();

    public void setBookCategories(List<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {

        String[] splitPublishedDate = publishedDate.split("-");

        LocalDateTime newPublishedDate = LocalDateTime.of(Integer.parseInt(splitPublishedDate[0]),
                Integer.parseInt(splitPublishedDate[1]), Integer.parseInt(splitPublishedDate[2]), 0, 0);

        this.publishedDate = newPublishedDate;
    }
}
