package com.company.example_4;

public class Main {
    //monitor
    //mutex (mutual exclusion)
    //intrinsic lock

    public static void main(String args[]) throws InterruptedException {

        CommonPrinter commonPrinter = new CommonPrinter();

        Worker T1 = new Worker( "Thread - 1 ", commonPrinter );
        Worker T2 = new Worker( "Thread - 2 ", commonPrinter );

        T1.start();
        T2.start();

        T1.join();
        T2.join();

    }
}

class CommonPrinter {

    public void printCount() {
        for(int i = 5; i > 0; i--) {

            //5
            //4
            //3
            //2
            //1
            System.out.println("Counter   ---   "  + i );
        }
    }
}

class Worker extends Thread {

    private String threadName;

    //State
    //MUTEX - Mutual exclusion
    //        Общее  исключение

    //Object -> monitor
    final CommonPrinter commonPrinter;

    Worker( String name,  CommonPrinter commonPrinter) {
        threadName = name;
        this.commonPrinter = commonPrinter;
    }

    public void run() {
        //В состоянии MUTEX, у монитора вызывают intrinsic lock
        //Когда есть общий, у "контролирующего" объекта вызывают блокировку


        //thread 2
        synchronized (commonPrinter) { //No not disturb

            //intrinsic lock   -> boolean on object level
            commonPrinter.printCount();
            System.out.println("Thread " + threadName + " exiting.");
        }
        //thread 1
    }

}