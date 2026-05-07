package bookstoread;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BookShelfSpec {

    private BookShelf shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() {
        shelf = new BookShelf();

        effectiveJava = new Book("Effective Java", "Bloch", LocalDate.of(2008, 5, 8));
        codeComplete = new Book("Code Complete", "McConnell", LocalDate.of(2004, 6, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Brooks", LocalDate.of(1975, 1, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, Month.AUGUST, 1));
    }

    @Test
    public void shelfEmptyWhenNoBookAdded() {
        assertTrue(shelf.books().isEmpty());
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        assertEquals(2, shelf.books().size());
    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();
        assertTrue(shelf.books().isEmpty());
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();

        assertThrows(UnsupportedOperationException.class, () -> {
            books.add(mythicalManMonth);
        });
    }

    @Test
    void bookshelfArrangedByBookTitle() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        List<Book> books = shelf.arrange();

        assertEquals(
                asList(mythicalManMonth, codeComplete, effectiveJava),
                books
        );
    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        shelf.arrange(); // ne doit PAS modifier

        assertEquals(
                asList(effectiveJava, codeComplete, mythicalManMonth),
                shelf.books()
        );
    }
    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(asList(effectiveJava, codeComplete, mythicalManMonth), books, () -> "Books in a bookshelf are arranged in descending order of book title");
    }
    @Test
    void shelfSortedByPublicationDate() {
        // Arrange : On ajoute les livres dans le désordre
        shelf.add(effectiveJava);    // 2008
        shelf.add(mythicalManMonth); // 1975
        shelf.add(codeComplete);     // 2004

        // On récupère la liste qui doit être triée
        List<Book> sortedBooks = shelf.arrange();

        // On vérifie l'ordre chronologique
        assertEquals(mythicalManMonth, sortedBooks.get(0));
        assertEquals(codeComplete, sortedBooks.get(1));
        assertEquals(effectiveJava, sortedBooks.get(2));
    }
    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();
        assertThat(booksByPublicationYear).containsKey(Year.of(2008)).containsValues(Arrays.asList(effectiveJava, cleanCode));
        assertThat(booksByPublicationYear).containsKey(Year.of(2004)).containsValues(Collections.singletonList(codeComplete));
        assertThat(booksByPublicationYear).containsKey(Year.of(1975)).containsValues(Collections.singletonList(mythicalManMonth));
    }
    @Nested
    @DisplayName("Est vide")
    class IsEmpty {
        @Test
        @DisplayName("Quand aucun livre n'y est ajouté")
        public void emptyBookShelfWhenNoBookAdded() {
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "BookShelf devrait être vide.");
        }
        @Test
        @DisplayName("Quand add est appelé sans livres")
        void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            shelf.add();
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "BookShelf devrait être vide.");
        }
    }

    @Nested
    @DisplayName("Après avoir ajouté des livres")
    class BooksAreAdded {
        @Test
        @DisplayName("Contient deux livres")
        void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
            // Test case removed for brevity
        }
        @Test
        @DisplayName("Renvoie au client une collection de livres immuable ")
        void bookshelfIsImmutableForClient() {
            // Test case removed for brevity
        }
    }
}