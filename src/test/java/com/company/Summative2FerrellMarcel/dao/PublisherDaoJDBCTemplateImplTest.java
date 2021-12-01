package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoJDBCTemplateImplTest {
    @Autowired
    PublisherDao dao;
    @Before
    public void setUp() throws Exception{
        List<Publisher> publisherList = dao.getAllPublishers();

        for(Publisher publisher: publisherList){
            dao.deletePublisher(publisher.getPublisherId());
        }
    }

    @Test
    public void addGetPublisher(){

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
        publisher = dao.addPublisher(publisher);
        Publisher publisher2 = dao.getPublisher(publisher.getPublisherId());
        //Assert
        assertEquals( publisher2, publisher);

        dao.deletePublisher(publisher.getPublisherId());
        publisher2 = dao.getPublisher(publisher.getPublisherId());

        //Assert
        assertNull(publisher2);
    }

    @Test
    public void getAllPublishers(){

        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Mars");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");

        //Act
        dao.addPublisher(publisher);

        //Arrange
        publisher = new Publisher();
        publisher.setName("Ferrell");
        publisher.setStreet("Ferrell rd");
        publisher.setCity("South Boston");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("55555");
        publisher.setEmail("testing@gmail.com");

        //Act
        dao.addPublisher(publisher);
        List<Publisher> publisherList = dao.getAllPublishers();

        //Assert
        assertEquals(publisherList.size(), 2);

    }
    @Test
    public void updatePublisher(){
        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Mars");
        publisher.setStreet("Ferrell trail");
        publisher.setCity("Danville");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("4343433443");
        publisher.setEmail("test@gmail.com");

        //Act
        dao.addPublisher(publisher);

        //Arrange
        publisher.setName("Marcel");
        publisher.setStreet("Ferrell rd");
        publisher.setCity("South Boston");
        publisher.setState("Va");
        publisher.setPostalCode("23503");
        publisher.setPhone("55555");
        publisher.setEmail("testing@gmail.com");

        //act
        dao.updatePublisher(publisher);
        Publisher publisher2 = dao.getPublisher(publisher.getPublisherId());

        //Assert
        assertEquals(publisher2, publisher);
    }
}
