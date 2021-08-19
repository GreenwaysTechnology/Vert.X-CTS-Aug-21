package com.cts.fp.builtinfun.interfaces;

public class RunnableMain {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
    }
}
