package dev.michaelodusami.functionalstyle;

import java.util.stream.IntStream;

public class StepLoops {
    
    public static void stepLoop()
    {
        for(int i = 0; i < 15; i = i + 3) {
            System.out.println(i);
        }
          
    }

    public static void functiaonlStepLoop()
    {
        IntStream.iterate(0, i -> i < 15, i -> i + 3).forEach(System.out::println);
    }
}
