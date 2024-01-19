package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://m.media-amazon.com/images/I/613dAp7dyRL._AC_UF1000,1000_QL80_.jpg",
                "A work of maddening brilliance", "Long description"));
        allBooks.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/v2/resize:fit:640/format:webp/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought", "Long description"));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public Book getBookById(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean addToFavorite(Book book) {
        return favoriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean removeFromFavorite(Book book) {
        return favoriteBooks.remove(book);
    }

    public boolean removeFromAlreadyRead(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReading(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book) {
        return wantToReadBooks.remove(book);
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }


}
