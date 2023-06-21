package com.example.realtimedatabasekotlin;

public class Person
{
    String Name;
    Integer Age;

    public Person(String name, Integer age) {
        this.Name = name;
        this.Age = age;
    }
    //empty constoctur firebase use this costroctur
    public Person() {
    }

    public String getName() {
        return Name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setAge(Integer age) {
        this.Age = age;
    }
}
