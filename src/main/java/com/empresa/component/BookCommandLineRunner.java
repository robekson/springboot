package com.empresa.component;

import com.empresa.elastich.model.Book;
import com.empresa.elastich.repository.BookRepository;
import com.empresa.entities.Beer;
import com.empresa.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@EnableElasticsearchRepositories(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = ElasticsearchRepository.class))
public class BookCommandLineRunner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

   /* public BookCommandLineRunner(BeerRepository repository) {
        this.repository = repository;
    }*/

    @Override
    public void run(String... strings) throws Exception {

      /*  Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        // Book testBook = bookService.save(book);
        bookRepository.save(book);*/
        System.out.println(bookRepository.findAll());
    }
}