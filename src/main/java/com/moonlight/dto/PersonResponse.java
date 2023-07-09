package com.moonlight.dto;

import com.moonlight.collections.Person;
import lombok.Data;

@Data
public class PersonResponse {
    private String status;
    private Integer statusCode;
    private Object person;
    private String message;
}
