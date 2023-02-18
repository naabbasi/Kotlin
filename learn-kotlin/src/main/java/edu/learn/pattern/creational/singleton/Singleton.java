package edu.learn.pattern.creational.singleton;

import java.io.Serializable;

public class Singleton implements Serializable{
    private static Singleton INSTANCE;

    private Singleton(){}

    public static Singleton getInstance() {
        if(INSTANCE == null){
            synchronized (Singleton.class){
                INSTANCE = new Singleton();
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);

        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        System.out.println(singletonEnum.getIntance());
    }
}
