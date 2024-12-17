package dev.michaelodusami.functionalstyle;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ForEachLoops {
    private final static List<String> names = List.of("Jack", "Paula", "Kate", "Peter");

    public static void functionalstyle()
    {
        names.forEach(name -> System.out.println(name));
    }

    public static void functionalstyleStream()
    {
        names.stream().forEach(name -> System.out.println(name));
    }

    public static void filtering()
    {
        // imperative

        for (String name: names) 
        {
            
            if (name.length() == 4) 
            {
                System.out.println(name);
            }
        }

        Predicate<String> isLengthOfFour = name -> name.length() == 4;
        names.stream().filter(isLengthOfFour).forEach(name -> System.out.println(name));
    }

    public static void mapping()
    {
        // original
        for (String name : names)
        {
            String n = name.toUpperCase();
            System.out.println(n);
        }

        List<String> newList = names.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println(newList);

        // imperative
        names.stream().map(name -> name.toUpperCase()).forEach(nameInUpperCase -> System.out.println());
    }

    public static void playground()
    {
        List<Boolean> values = names.stream().map(name -> {
            if (name.length() <= 4)
            {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println(values);
    }
}
