package com.moonlight.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String address1;
    private String city;
    private String state;
    private Integer pinCode;
}
