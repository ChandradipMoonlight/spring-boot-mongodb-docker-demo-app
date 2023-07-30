package com.moonlight.java8practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortListByJava8 {

    public static List<Employee> employeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "Dipak", "Kumar", Departments.IT.name(), 50000));
        employees.add(new Employee(3, "Ram", "Suryavanshi", Departments.ENGINEERING.name(), 90000));
        employees.add(new Employee(10, "Bhushan", "Kulkarni", Departments.OPERATION.name(), 45000));
        employees.add(new Employee(1, "Abhijit", "Kulkarni", Departments.ENGINEERING.name(), 80000));
        employees.add(new Employee(2, "Dipak", "Janjal", Departments.FINANCE.name(), 60000));
        return employees;
    }

    public static void sortListIntegerUsingStreamAPI() {
        List<Integer> numbers = List.of(4, 3, 5, 2, 7, 1);
        numbers.stream().sorted().forEach(e -> System.out.println(e)); // sort in Ascending order
        // sort in Descending order
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(e -> System.out.println(e));
    }

    public static void sortEmployeeList() {
        //sorting using implementing comparator interface implementation
        employeeList().stream().sorted((o1, o2) -> Math.toIntExact(o1.getSalary() - o2.getSalary())).
                forEach(employee -> System.out.println(employee));
        //sorting using Comparator.Comparing method by Department of employee
        System.out.println("Sorting employee list by Department");
        employeeList().stream().sorted(Comparator.comparing((Employee::getDepartment)))
                .forEach(employee -> System.out.println(employee));
    }

    public static void main(String[] args) {
//        sortListIntegerUsingStreamAPI();
        sortEmployeeList();
    }
}
