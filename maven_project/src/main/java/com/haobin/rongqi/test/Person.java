package com.haobin.rongqi.test;

public class Person implements Comparable<Person> {
    int score;
    String name;

    public Person(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person [score=" + score + ", name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Person o) {
        return this.score - o.score;
    }
}
