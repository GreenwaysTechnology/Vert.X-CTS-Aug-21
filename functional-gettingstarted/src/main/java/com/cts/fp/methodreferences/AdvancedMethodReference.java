package com.cts.fp.methodreferences;


class MicroTask {
    public static void startMicroTaskV2() {
        System.out.println(Thread.currentThread().getName());
    }

    public void startMicroTask() {
        System.out.println(Thread.currentThread().getName());
    }
}

class Task {
    //Runnable logic method
    public void process() {
        System.out.println(Thread.currentThread().getName());
    }

    public void startTask() {
        //Thread creation using multiple ways
        Thread thread = null;
        //anonmous class
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
        //Using Lambda
        thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
        //Runnable logic into another method : using lambda
        thread = new Thread(() -> this.process());
        thread.start();
        //Runnable logic into another method : using method Reference
        thread = new Thread(this::process);
        thread.start();
        //Runnable logic into another class as instance method:
        MicroTask microTask = new MicroTask();
//        thread = new Thread(() -> microTask.startMicroTask());
        thread = new Thread(microTask::startMicroTask);
        thread.start();
//        thread = new Thread(() -> microTask.startMicroTask());
        thread = new Thread(MicroTask::startMicroTaskV2);
        thread.start();


    }

}

public class AdvancedMethodReference {
    public static void main(String[] args) {
        Task task = new Task();
        task.startTask();
    }
}
