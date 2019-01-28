package com.imse.imse.DataInsert;

import java.util.Random;

public enum Surnames {

    Smith,Johns, Bauer, Wiliams, Taylor, Brand, Davies, Evans,Willson, Robinson, Roberts, Johnson,
    Walker, White, Edward, Green , Hall, Lewis, Harris,  Jackson, Wood, Tenor, Kumer, Kaminski,
    Erguven;


    public static Surnames getRandomSurname(){
        Random random = new Random();
        return values() [random.nextInt(values().length)];
    }
}

