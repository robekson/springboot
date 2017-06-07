package com.empresa.elastich.service;

/**
 * Created by cyborg on 6/3/17.
 */
import com.empresa.elastich.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {

    Book save(Book book);

    void delete(Book book);

    Book findOne(String id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, PageRequest pageRequest);

    List<Book> findByTitle(String title);

}