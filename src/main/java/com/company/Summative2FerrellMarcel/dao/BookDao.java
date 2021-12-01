package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Book;
import java.util.List;

public interface BookDao{

    Book getBook(int bookId);
    List<Book> getAllBooks();
    Book addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
    List <Book> getBookByAuthor(int authorId);

}
