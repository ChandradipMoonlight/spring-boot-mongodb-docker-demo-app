package com.moonlight.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Transient
    public static final String SEQUENCE_NAME = "persons_sequence";
    @Id
    private Integer personId;
    private String firstName;
    private String lastName;
    private String age;
    private List<String> hobbies;
    private List<Address> addresses;
}
