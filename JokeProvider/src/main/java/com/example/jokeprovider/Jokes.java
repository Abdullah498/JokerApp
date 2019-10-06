package com.example.jokeprovider;

import java.util.Random;

public class Jokes {

    private final String jokeArray [] = {"Can a kangaroo jump higher than a house? \n" +
            "-\n" +
            "Of course, a house doesn’t jump at all.\n",
            "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                    "\n" +
                    "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                    "\n" +
                    "Doctor: \"Nine.\"\n",
            "Anton, do you think I’m a bad mother?\n" +
                    "\n" +
                    "My name is Paul.\n" +
                    "\n",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.\n" +
                    "\n",
            "What is the difference between a snowman and a snowwoman?\n" +
                    "-\n" +
                    "Snowballs.\n" +
                    "\n"};

    public String getAJoke(){
        Random random = new Random();
        return jokeArray[random.nextInt(jokeArray.length-1)];
    }


}
