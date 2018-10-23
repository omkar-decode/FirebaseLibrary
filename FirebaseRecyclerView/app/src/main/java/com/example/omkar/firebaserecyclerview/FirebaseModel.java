package com.example.omkar.firebaserecyclerview;

/**
 * Created by Omkar on 18-02-2018.
 */

public class FirebaseModel {
    String bookName, bookAuthor, bookPicUrl;
    int bookId;

    public FirebaseModel(String bookName, String bookAuthor, String bookPicUrl, int bookId) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPicUrl = bookPicUrl;
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookPicUrl() { return bookPicUrl; }

    public void setBookPicUrl(String bookPicUrl) { this.bookPicUrl = bookPicUrl; }
}
