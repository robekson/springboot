package com.empresa.elastich.model;

import com.empresa.entities.Beer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Created by cyborg on 5/29/17.
 */
@Document( indexName = "person" , type = "beer")
@Data
public class Person {


    @Id
    private String id;

    private String name;

    @Field( type = FieldType.Nested)
    private List<Beer> car;


    // setters-getters

}
