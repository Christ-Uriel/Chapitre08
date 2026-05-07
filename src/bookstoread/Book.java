package bookstoread;

import java.time.LocalDate;
/**
 * Représente un livre avec un titre, un auteur et une date de publication.
 * Implémente Comparable pour permettre un tri par défaut.
 */
public class Book implements Comparable<Book> {

    private final String title;
    private final String author;
    private final LocalDate publishedOn;
    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public LocalDate getPublishedOn() {
        return publishedOn;
    }
    /**
     * Définit l'ordre naturel de tri des livres.
     * Ici, le tri est effectué par date de publication (ordre chronologique).
     */
    @Override
    public int compareTo(Book that) {
        return this.publishedOn.compareTo(that.publishedOn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedOn=" + publishedOn +
                '}';
    }
}
