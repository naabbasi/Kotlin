package edu.learn.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
        //stream.map { it }.filter{it >= 5}.forEach { println(it) }
        List<Integer> list = stream.filter(r -> r >= 5 ).collect(Collectors.toList());
        System.out.println(list);
    }
}
