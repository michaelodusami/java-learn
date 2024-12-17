package dev.michaelodusami.functionalstyle;

import java.util.stream.IntStream;

public class SimpleLoops {
    
    public static void simpleLoop()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(i);
        }
    }

    public static void simpleFunctionalLoop()
    {
        IntStream.range(0, 5).forEach(number -> System.out.println(number));
    }

    public static void simpleFunctionalConciseLoop()
    {
        IntStream.range(0, 5).forEach(System.out::println);
    }

    public static void simpleFunctionalClosedLoop()
    {
        IntStream.rangeClosed(0, 5).forEach(number -> System.out.println(number));
    }
}
