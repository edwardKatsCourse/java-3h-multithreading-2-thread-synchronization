package com.company.example_3;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int count = 100_000;

        PostWindow postWindow = new PostWindow(count);

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            customers.add(new Customer("Customer #" + (i+1), postWindow));
        }

        for (Customer customer : customers) {
            customer.start();
        }

        for (Customer customer : customers) {
            customer.join();
        }


        System.out.println(postWindow.packagesLeft());

    }
}


class Customer extends Thread {
    private String name;
    private PostWindow postWindow;

    public Customer(String name, PostWindow postWindow) {
        this.name = name;
        this.postWindow = postWindow;
    }

    @Override
    public void run() {

        postWindow.getPackage();
    }
}

class PostWindow {


    private int packageCount;

    //private Person person;

    public static void getName() {

        //thread 2
        synchronized (PostWindow.class) {
        }
    }

    public synchronized static void getAddress() { //-> PostWindow.class

        //thread 1
    }

    public PostWindow(int packageCount) {
        this.packageCount = packageCount;
    }

    public /*synchronized*/ int getPackage() { //-> this


        //State                     ->  race condition (depending on who reached this line first)
        //postWindow.getPackage();  ->  critical section

        //------------ WAIT HERE ------------//
        //synchronized          -> wait (one by one)
        //(PostWindow.class)    -> waiting for what??


        //PostWindow postWindow = new PostWindow(0);
        //postWindow.getPackage();
        //PostWindow.class

        //synchronized(postWindow)
        //synchronized(PostWindow.class)


        synchronized (PostWindow.class) {

            --packageCount;

            //happens before
            //person = new Person();
        }

        return packageCount;
    }

    public int packagesLeft() {
        return packageCount;
    }
}