package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Author;

import java.util.List;


public interface AuthorDao{

    Author getAuthor(int authorId);
    List<Author> getAllAuthors();
    Author addAuthor(Author author);
    void updateAuthor(Author author);
    void deleteAuthor(int authorId);

}
