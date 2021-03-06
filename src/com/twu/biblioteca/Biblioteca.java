package com.twu.biblioteca;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

    private ArrayList<Book> books = new ArrayList();
    private ArrayList<Movie> movies = new ArrayList<>();
    private HashMap<Book, User> checkedoutBooks = new HashMap<>();

    Biblioteca() {
        InitializingLibraryBooks();
        InitializingLibraryMovies();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Boolean CheckoutBook(String bookName, User user) throws IOException {
        try {
            Book book = FindBookByName(bookName);
            if (!checkedoutBooks.containsKey(book)) {
                checkedoutBooks.put(book, user);
                return true;
            } else
                return false;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Boolean CheckoutMovie(String movieName) throws IOException {
        try {
            Movie movie = FindMovieByName(movieName);
            if (movie.getAvailable()) {
                movie.setAvailable(false);
                return true;
            } else
                return false;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Boolean ReturnBook(String bookName) throws IOException {
        try {
            Book book = FindBookByName(bookName);
            if (checkedoutBooks.containsKey(book)) {
                checkedoutBooks.remove(book);
                return true;
            } else
                return false;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Boolean ReturnMovie(String movieName) throws IOException {
        try {
            Movie movie = FindMovieByName(movieName);
            if (!movie.getAvailable()) {
                movie.setAvailable(true);
                return true;
            } else
                return false;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Book FindBookByName(String name) throws IOException {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        throw new IOException("We don't have this book.");
    }

    public Movie FindMovieByName(String name) throws IOException {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        throw new IOException("We don't have this movie.");
    }

    public ArrayList<Book> BooksCheckedOut() {
        ArrayList<Book> booksCheckOut = new ArrayList<>();
        for (Book book : books) {
            if (!book.getAvailable())
                booksCheckOut.add(book);
        }
        return booksCheckOut;
    }

    public void AddBook(Book book) {
        books.add(book);
    }

    public void AddMovie(Movie movie) {
        movies.add(movie);
    }

    public void InitializingLibraryBooks() {
        Book book1 = new Book("Test-Driven Development by example", "Kent Beck", 2000);
        books.add(book1);

        Book book2 = new Book("Extreme Programming Explained", "Kent Beck", 1999);
        books.add(book2);

        Book book3 = new Book("Implementation Patterns", "Kent Beck", 2007);
        books.add(book3);
    }

    public void InitializingLibraryMovies() {
        Movie movie1 = new Movie("Godfather", "Francis Coppola", 1972, 8);
        movies.add(movie1);

        Movie movie2 = new Movie("Thelma & Louise", "Ridley Scott", 1991, 8);
        movies.add(movie2);

        Movie movie3 = new Movie("Lua de Cristal", "Tizuka Yamasaki", 1990, 10);
        movies.add(movie3);
    }

}
