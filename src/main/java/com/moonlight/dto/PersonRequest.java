package com.moonlight.dto;

import com.moonlight.collections.Address;
import lombok.Data;

import java.util.List;

@Data
public class PersonRequest {
    private String firstName;
    private String lastName;
    private String age;
    private List<String> hobbies;
    private List<Address> addresses;
}
