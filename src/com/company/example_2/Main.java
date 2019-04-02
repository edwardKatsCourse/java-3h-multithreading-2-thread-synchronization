package com.company.example_2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Toy commonToy = new Toy();

        List<Child> children = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            children.add(new Child(commonToy, "some name " + (i+1)));
        }


        for (Child child : children) {
            child.start();
        }

        for (Child child : children) {
            child.join();
        }

        System.out.printf("Children: %s\n", children.size());
        System.out.printf("Played times: %s", commonToy.getHoursPlayed());


    }
}


class Child extends Thread {

    private Toy toy;

    //CPU memory cache
    private Toy toyCache;

    public Child(Toy toy, String name) {
        this.toy = toy;
        this.setName(name);
    }

    @Override
    public void run() {

        //>>>>>>>>>race condition start
        //race      -> threads' work (fast)
        //condition -> whoever gets first - does the job right

        //order is not determined - последовательность не определена (не гарантирована)
        //--------------------- WAIT _ HERE ----------------------------//

//        synchronized (Toy.class) {

            //toy.hours = 1


            //CPU cache memory coherence
            toyCache = toy;


            toyCache.play();

            toy = toyCache;
//        }

        //>>>>>>>>>race condition end

        //thread 2
        //toyCache.hours = 2

        //toy.hours = 2

        //thread 1
        //toyCache.hours = 2

    }
}

class Toy {
    private int hoursPlayed; //10


    public void play() {
        hoursPlayed++;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }
}