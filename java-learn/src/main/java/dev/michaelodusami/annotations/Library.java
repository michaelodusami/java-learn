package dev.michaelodusami.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
    String name();
    String date();
}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RequiresPermission {
    String value();
}

@FunctionalInterface
interface BookSorter {
    void sortBooks();
}

@Author(name = "Alice Smith", date = "12/16/2024")
public class Library {
    
    @Deprecated(forRemoval = true)
    public void oldSearch(){
        System.out.println("This method is deprecated. Use searchBook() instead.");
    }

    @Override
    public String toString(){
        return "Library System";
    }

    @SuppressWarnings("unchecked")
    public void addBooks()
    {
        @SuppressWarnings("rawtypes")
        List booklist = new ArrayList<>();
        booklist.add("book2");
        System.out.println("Books added: " + booklist);
    }

    @RequiresPermission("Admin")
    public void manageBooks()
    {
        System.out.println("Managing Books...");
    }
}
