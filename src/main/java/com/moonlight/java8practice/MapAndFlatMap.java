package com.moonlight.java8practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMap {

    public static List<Customer> getAllCustomersData() {
        return List.of(
                new Customer(2, "Chandradip", Arrays.asList("92453888092", "8764563674")),
                new Customer(5, "Bhushan", Arrays.asList("72453888092", "9764563674")),
                new Customer(3, "Ram", Arrays.asList("82453788092", "7764563674")),
                new Customer(4, "Dipak", Arrays.asList("82453885092", "8768453674"))
                );

    }

    public static void mapMethodEx() {
        System.out.println("Get List of customersName form customer Data by using stream.map() method!");
        List<String> customerNames = getAllCustomersData()
                .stream()
                .map(customer -> customer.getName())
                .collect(Collectors.toList());
        System.out.println(customerNames);
    }

    public static void flatMapEx() {
        System.out.println("Get List of all mobiles from customer data");
        System.out.println("Using stream.map() method");
        List<List<String>> customerMobiles = getAllCustomersData()
                .stream()
                .map(customer -> customer.getMobileNumbers())
                .collect(Collectors.toList());
        System.out.println(customerMobiles);

        // flatMap() method takes imput as Stream of Stream and return Stream [Stream<Stream<T>>]

        System.out.println("Using stream.flatMap() method");

        List<String> mobiles = getAllCustomersData()
                .stream()
                .flatMap(customer -> customer.getMobileNumbers().stream())
                .collect(Collectors.toList());
        System.out.println(mobiles);
    }

    public static void main(String[] args) {
        mapMethodEx();
        flatMapEx();
    }
}
