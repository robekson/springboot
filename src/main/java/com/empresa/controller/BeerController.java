package com.empresa.controller;

/**
 * Created by cyborg on 5/28/17.
 */
import com.empresa.elastich.model.Book;
import com.empresa.elastich.service.BookService;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.empresa.repositories.BeerRepository;
import com.empresa.entities.Beer;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;


@RestController
@EnableAutoConfiguration
public class BeerController {

    @Autowired
    private BeerRepository repository;

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;

    List<String> names = new ArrayList<String>();

    @ResponseBody
    @RequestMapping(value="/api/names", method= RequestMethod.GET)
    public ResponseEntity<List<String>> get() {
        return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }


    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    public BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/good-beers")
    public Collection<Map<String, String>> goodBeers() {

        return repository.findAll().stream()
                .filter(this::isGreat)
                .map(b -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("name", b.getName());
                    return m;
                }).collect(Collectors.toList());
    }

    //-------------------Create a Beer--------------------------------------------------------

    @RequestMapping(value = "/beer/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Beer beer,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + beer.getName());

        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        repository.save(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(beer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Beer --------------------------------------------------------

    @RequestMapping(value = "/beer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Beer> updateUser(@PathVariable("id") long id, @RequestBody Beer beer) {
        System.out.println("Updating User " + id);

        Beer currentBeer = repository.findOne(id);

        if (currentBeer==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Beer>(HttpStatus.NOT_FOUND);
        }

        currentBeer.setId(beer.getId());
        currentBeer.setName(beer.getName());

        repository.save(beer);
        return new ResponseEntity<Beer>(currentBeer, HttpStatus.OK);
    }


    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Budweiser") &&
                !beer.getName().equals("Coors Light") &&
                !beer.getName().equals("PBR");
    }


    //useful for debug, print elastic search details
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch--");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--ElasticSearch--");
    }


    @RequestMapping("/search")
    public Map<String,Object> search() {
        Map<String,Object> model = new HashMap<String,Object>();

        printElasticSearchInfo();
        /*
        bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        */
        //fuzzey search
        Page<Book> books = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));

        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");

        books.forEach(x -> System.out.println(x));


        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }



}

class Greeting {

    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}