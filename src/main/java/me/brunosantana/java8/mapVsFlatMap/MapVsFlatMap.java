package me.brunosantana.java8.mapVsFlatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Exemplo baseado em https://www.youtube.com/watch?v=CwvlS3ViGFQ
// Codigo original no GitHub: https://github.com/Java-Techie-jt/java8/tree/master/mapVsflatMap

public class MapVsFlatMap {
    public static void main(String[] args) {

        List<Customer> customers = Stream.of(
                new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")),
                new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947")),
                new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236")),
                new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467"))
        ).collect(Collectors.toList());

        //List<Customer> convert to List<String> -> Data Transformation
        //mapping : customer -> customer.getEmail()
        //customer -> customer.getEmail()  one to one mapping
        List<String> emails = customers.stream()
                .map(customer -> customer.getEmail())
                .collect(Collectors.toList());
        System.out.println(emails);

        //customer -> customer.getPhoneNumbers() -> one to many mapping
        List<List<String>> phoneNumbers = customers.stream()
                .map(customer -> customer.getPhoneNumbers())
                .collect(Collectors.toList());
        System.out.println(phoneNumbers);

        //List<Customer> convert to List<String> -> Data Transformation
        //mapping : customer -> phone Numbers
        //customer -> customer.getPhoneNumbers() -> one to many mapping
        List<String> phones = customers.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toList());
        System.out.println(phones);
    }
}
