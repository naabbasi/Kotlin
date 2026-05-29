package edu.learn.functional.intrface.demo;

public class Cat {

    public void run(Animal animal) {
        System.out.println("I m functional interface: " + animal.walk());
    }
}
