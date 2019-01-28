package com.imse.imse.DataInsert;


import java.util.Random;

public enum Names {

    Adrian, Max, Martin, Yasin, Mattheus, Yifan, Oliver, Dominik, Marian;

    public static Names getRandomName(){
        Random random =  new Random();
        return values() [random.nextInt(values().length)];
    }
}


