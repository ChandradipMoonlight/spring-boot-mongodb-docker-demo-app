package com.moonlight.java8practice;

import java.util.*;

import static java.util.Map.entry;

public class SortMap {

    public static Map<Integer, Employee> getEmployeeMap() {
        return Map.ofEntries(
                entry(4, new Employee(4, "Dipak", "Kumar", Departments.IT.name(), 50000)),
                entry(10, new Employee(10, "Bhushan", "Kulkarni", Departments.OPERATION.name(), 45000)),
                entry(1, new Employee(1, "Abhijit", "Kulkarni", Departments.ENGINEERING.name(), 80000)),
                entry(2, new Employee(2, "Dipak", "Janjal", Departments.FINANCE.name(), 60000))
        );
    }

    public static void sortEmployeeMap() {
        List<Map.Entry<Integer, Employee>> emps = new ArrayList<>(getEmployeeMap().entrySet());
       Collections.sort(emps, (o1, o2) -> o1.getValue().getDepartment().compareTo(o2.getValue().getDepartment()));
       emps.forEach(e -> System.out.println(e));
        System.out.println("\nSorting map by value by firstName in Descending order using stream\n");
       getEmployeeMap().entrySet().stream()
               .sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getFirstName).reversed()))
               .forEach(entry -> System.out.println(entry));
    }

    public static void main(String[] args) {
        sortEmployeeMap();
    }
}
