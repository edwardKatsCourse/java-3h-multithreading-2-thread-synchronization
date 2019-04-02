package com.company.example_1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static boolean flag = false;


    public static void main(String[] args) throws InterruptedException {

        Incrementator commonResource = new Incrementator(0);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new Runnable() {

                private String name = "John";

                @Override
                public void run() {
                    commonResource.increment();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }


        System.out.println(commonResource.getCounter());
    }
}

class Incrementator {

    private Integer counter;

    public Incrementator(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void increment() {
        this.counter++;
    }
}