package com.example.demo;


import org.springframework.stereotype.Service;

/**
 * Created by atulsahaney on 9/12/17.
 */

@Service
public class greetPerson {

    private String greeting;
    private int apples;
    private String greetPets;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getApples() {
        return apples;
    }

    public void setApples(int apples) {
        this.apples = apples;
    }

    public String getGreetPets() {
        return greetPets;
    }

    public void setGreetPets(String greetPets) {
        this.greetPets = greetPets;
    }

    public greetPerson greetPerson(person person) {

        greetPerson greet = new greetPerson();
        greet.setGreeting(greeting(_person.getName()));
        greet.setApples(howManyApples(_person.getApples()));
        greet.setGreetPets(greetPets(_person.getPets()));

        return greet;
    }

    private String greeting(String name) {
        return "Hi " + name + " , how are you?";
    }

    private int howManyApples(int apples) {
        return apples;
    }

    private String greetPets(String[] pets) {

        return null;
    }
}
