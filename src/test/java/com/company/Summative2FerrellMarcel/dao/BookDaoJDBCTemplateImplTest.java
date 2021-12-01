package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Author;
import com.company.Summative2FerrellMarcel.dto.Book;
import com.company.Summative2FerrellMarcel.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJDBCTemplateImplTest {

    @Autowired
    BookDao bookDao;
    @Autowired
    AuthorDao authorDao;
    @Autowired
    PublisherDao publisherDao;
    @Before
    public void setUp() throws Exception{
        List<Book> bookList = bookDao.getAllBooks();
        for(Book book: bookList){
            bookDao.deleteBook(book.getBookId());
        }

        List<Publisher> publisherList = publisherDao.getAllPublishers();
        for (Publisher publisher : publisherList){
            publisherDao.deletePublisher(publisher.getPublisherId());
        }

        List<Author> authorList = authorDao.getAllAuthors();
        for(Author author : authorList){
            authorDao.deleteAuthor(author.getAuthorId());
        }
    }

    @Test
    public void addGetDeleteBook(){

        //Arrange
        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setStreet("Ferrell trail");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");


        //Act
        author = authorDao.addAuthor(author);

        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Belac");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");

        //Act
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("HI-3455");
        book.setPublishDate(LocalDate.of(2021, 11, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("StoryTime");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        book = bookDao.addBook(book);

        Book book2 = bookDao.getBook(book.getBookId());


        //Assert
        assertEquals(book2, book);

        bookDao.deleteBook(book.getBookId());
        book2 = bookDao.getBook(book.getBookId());

        //Assert
        assertNull(book2);
    }



    @Test
    public void getAllBooks() {

        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("Belac");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("TYZ-099");
        book.setPublishDate(LocalDate.of(1999, 4, 29));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("MyStory");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        book = bookDao.addBook(book);


        book = new Book();
        book.setIsbn("HI-3455");
        book.setPublishDate(LocalDate.of(2021, 11, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("StoryTime");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        book = bookDao.addBook(book);

        List<Book> bookList = bookDao.getAllBooks();

        assertEquals(2,bookList.size());
    }

    @Test
    public void getBookByAuthor(){

        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("Belac");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        //add first book
        Book book = new Book();
        book.setIsbn("TYZ-099");
        book.setPublishDate(LocalDate.of(1999, 4, 29));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("MyStory");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        book = bookDao.addBook(book);

        //add second book
        book = new Book();
        book.setIsbn("HI-3455");
        book.setPublishDate(LocalDate.of(2021, 11, 1));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("StoryTime");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        //Act
        bookDao.addBook(book);

        List<Book> bList = bookDao.getBookByAuthor(author.getAuthorId());

        //Assert
        assertEquals(2, bList.size());

    }

    @Test
    public  void updateBook(){

        //Need to create an author and publisher first
        Author author = new Author();
        author.setFirstName("Mars");
        author.setLastName("Belac");
        author.setStreet("Ferrell trail");
        author.setCity("Danville");
        author.setState("VA");
        author.setPostalCode("23503");
        author.setPhone("4343433443");
        author.setEmail("test@gmail.com");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("Belac");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        //add first book
        Book book = new Book();
        book.setIsbn("TYZ-099");
        book.setPublishDate(LocalDate.of(1999, 4, 29));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("MyStory");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("9.99"));

        book = bookDao.addBook(book);


        //new information to be updated
        book.setTitle("New Title");
        book.setPublishDate(LocalDate.of(2021, 11, 1));
        book.setPrice(new BigDecimal("19.99"));

        bookDao.updateBook(book);  //updates book

        Book book2 = bookDao.getBook(book.getBookId());
        assertEquals(book2, book);

    }
}