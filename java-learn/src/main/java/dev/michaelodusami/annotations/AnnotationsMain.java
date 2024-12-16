package dev.michaelodusami.annotations;


public class AnnotationsMain {
    
    public static void runMain()
    {
        Library lib = new Library();
        
        lib.oldSearch();

        BookSorter sorter = () -> System.out.println("Sorting books...");
        sorter.sortBooks();

        if (Library.class.isAnnotationPresent(Author.class))
        {
            Author author = Library.class.getAnnotation(Author.class);
            System.out.println("Library Author: " + author.name() + ", Date: " + author.date());
        }

        lib.manageBooks();
    }
}
