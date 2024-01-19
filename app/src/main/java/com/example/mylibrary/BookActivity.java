package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddtoAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }

    }

    /**
     * Enable and disable button
     * Add the book to Wishlist Book ArrayList
     *
     * @param book
     */
    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }

        if (existInWantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and disable button
     * Add the book to Currently reading book ArrayList
     *
     * @param book
     */
    private void handleCurrentlyReadingBooks(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /**
     * Enable and disable button
     * Add the book to Favorite book ArrayList
     *
     * @param book
     */
    private void handleFavoriteBooks(Book book) {
        ArrayList<Book> favoriteBooks = Utils.getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for (Book b : favoriteBooks) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
            }
        }

        if (existInFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and disable button
     * Add the book to Already Read Book ArrayList
     *
     * @param book
     */
    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            btnAddtoAlreadyRead.setEnabled(false);
        } else {
            btnAddtoAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Set all the data for the book
     *
     * @param book
     */
    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);
        btnAddtoAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        bookImage = findViewById(R.id.imgBook);
    }
}