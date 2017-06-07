package com.empresa.elastich.model;

/**
 * Created by cyborg on 6/3/17.
 */
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "index_roba", type = "books")
@Data
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String releaseDate;

    public Book() {
    }

    public Book(String id, String title, String author, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    //getters and setters

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
