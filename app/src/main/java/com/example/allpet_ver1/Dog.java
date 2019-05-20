package com.example.allpet_ver1;

import java.io.Serializable;

public class Dog implements Serializable {
    String name;
    int age;
    String gender;
    String operation;
    String from;
    String to;
    int price;
    String text;
    int want_cnt;

    public Dog(String name, int age, String gender, String operation, String from, String to, int price, String text, int want_cnt){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.operation=operation;
        this.from=from;
        this.to=to;
        this.price=price;
        this.text=text;
        this.want_cnt=want_cnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWant_cnt() { return want_cnt; }

    public void setWant_cnt(int want_cnt) { this.want_cnt = want_cnt; }
}
