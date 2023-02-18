package edu.learn.concurrent.semaphore.demo1;

import java.util.concurrent.Semaphore;

public class SemoDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new IncShared(semaphore, "A");
        new DecShared(semaphore, "B");
    }

    static class Shared {
        static int count = 0;
    }
}
