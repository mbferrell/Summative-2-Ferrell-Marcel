package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Author;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoJDBCTemplateImplTest {

    @Autowired
    AuthorDao dao;
    @Before
    public void setUp() throws Exception{
        List<Author> authorList = dao.getAllAuthors();

        for(Author author: authorList){
            dao.deleteAuthor(author.getAuthorId());
        }
    }

    @Test
    public void addAuthor(){

        //Arrange
        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");

        //Act
        author = dao.addAuthor(author);
        Author author2 = dao.getAuthor(author.getAuthorId());
        //Assert
        assertEquals(author, author2);

        dao.deleteAuthor(author.getAuthorId());
        author2 = dao.getAuthor(author.getAuthorId());

        //Assert
        assertNull(author2);
    }

    @Test
    public void getAllAuthors(){

        //Arrange
        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");


        //Act
        dao.addAuthor(author);

        //Arrange
        author = new Author();
        author.setFirstName("Marcel");
        author.setLastName("Ferrell");
        author.setStreet("Ferrell rd");
        author.setCity("South Boston");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("55555");
        author.setEmail("testing@gmail.com");


        //Act
        dao.addAuthor(author);
        List<Author> authorList = dao.getAllAuthors();

        //Assert
        assertEquals(authorList.size(), 2);

    }

    @Test
    public void updateAuthor(){
        //Arrange
        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");

        //Act
        author = dao.addAuthor(author);

        //Arrange
        author.setFirstName("Marcel");
        author.setLastName("Ferrell");
        author.setStreet("Ferrell rd");
        author.setCity("South Boston");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("55555");
        author.setEmail("testing@gmail.com");

        //act
        dao.updateAuthor(author);
        Author author2 = dao.getAuthor(author.getAuthorId());

        //Assert
        assertEquals(author2, author);
    }

}