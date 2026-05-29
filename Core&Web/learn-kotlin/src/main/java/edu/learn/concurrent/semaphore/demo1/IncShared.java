package edu.learn.concurrent.semaphore.demo1;

import java.util.concurrent.Semaphore;

public class IncShared implements Runnable {
    private Semaphore semaphore;
    private String name;

    public IncShared(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(this.name + "  requested to acquire");
        try{
            this.semaphore.acquire();
            System.out.println(this.name + "  gets permitted to acquire");
            for(int i = 0 ; i < 5; i++){
                System.out.println(SemoDemo.Shared.count++);
                Thread.sleep(500);
            }

            this.semaphore.release();
            System.out.println(this.name + " released");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
