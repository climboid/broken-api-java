package com.example.demo;

/**
 * Created by atulsahaney on 9/11/17.
 */

class person {

    private String name;
    private String[] pets;
    private int apples = 12;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPets() {
        return pets;
    }

    public void setPets(String[] pets) {
        this.pets = pets;
    }

    public int getApples() {
        return apples;
    }

    public void setApples(int apples) {
        this.apples = apples;
    }

}
