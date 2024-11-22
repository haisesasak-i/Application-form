package com.example.demo5;

public class Person {
    private String name;
    private String fatherName;
    private String cnic;
    private String date;
    private String gender;
    private String city;

    public Person(String name, String fatherName, String cnic, String date, String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.date = date;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + '\'' + ", fatherName='" + fatherName + '\'' +
                ", cnic='" + cnic + '\'' + ", date='" + date + '\'' +
                ", gender='" + gender + '\'' + ", city='" + city + '\'' + '}';
    }
}