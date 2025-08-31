package com.java.auth.helpers;

import java.util.Random;

public class RandomNumberHelpers {
    public static int randomNumberGenerator(){
        int min =1, max =1000;
        Random random = new Random();
        return random.nextInt(max-min)+ min ;
    }

}
