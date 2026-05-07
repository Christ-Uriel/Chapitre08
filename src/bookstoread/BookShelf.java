package bookstoread;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        // immutable
        return List.copyOf(books);
    }

    // Ajout de plusieurs livres à la fois
    public void add(Book... newBooks) {
        books.addAll(Arrays.asList(newBooks));
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }
    /**
     * Organise les livres selon un critère spécifique fourni en paramètre.
     * Utilise les Streams Java pour trier et collecter les résultats dans une nouvelle liste.
     */
    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
    private Book cleanCode;

    public Map<Year, List<Book>> groupByPublicationYear() {
        return books.stream()
                .collect(Collectors.groupingBy(book -> Year.of(book.getPublishedOn().getYear())));
    }




}