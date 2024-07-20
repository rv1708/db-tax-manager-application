package com.danske.taxmanager.utils;

import java.util.Random;

public class IDGenerator {

    private String type;

    private String id;

    public IDGenerator(String type) {
        this.type = type;
    }

    public String getIdFromType(){
        String firstLetter = String.valueOf(this.type.toUpperCase().charAt(0));
        Random rand = new Random();
        int randomNumber = rand.nextInt(100000, 999999);
        return firstLetter + "-" + String.valueOf(randomNumber);
    }
}
