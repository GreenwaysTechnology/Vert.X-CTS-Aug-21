package com.cts.rx.core;

import io.reactivex.rxjava3.core.Observable;

class Employee {
    private int id;
    private String name;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class ArrayOperator {
    public static void main(String[] args) {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable<Integer> stream = Observable.fromArray(items);
        stream.subscribe(System.out::println, System.out::println, () -> System.out.println("done"));

        //Employee Array
        Employee[] employees = {new Employee(1, "A"), new Employee(2, "B"), new Employee(3, "C")};
        Observable<Employee> empStream= Observable.fromArray(employees);
        empStream.subscribe(System.out::println, System.out::println, () -> System.out.println("done"));


    }
}
