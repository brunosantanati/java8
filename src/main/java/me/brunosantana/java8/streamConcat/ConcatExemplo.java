package me.brunosantana.java8.streamConcat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcatExemplo {

    public static void main(String[] args) {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(4, 5, 6);
        Stream<Integer> s3 = Stream.of(7, 8, 9);

        Stream<Integer> s1AndS2 = Stream.concat(s1, s2);
        Stream<Integer> s4 = Stream.concat(s1AndS2, s3);

        //s4.forEach(System.out::println);

        List<Integer> list = s4.collect(Collectors.toList());

        list.forEach(n -> System.out.println(n));
    }

}
