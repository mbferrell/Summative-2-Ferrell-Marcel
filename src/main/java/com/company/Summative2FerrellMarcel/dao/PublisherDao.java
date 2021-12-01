package com.company.Summative2FerrellMarcel.dao;

import com.company.Summative2FerrellMarcel.dto.Publisher;

import java.util.List;


public interface PublisherDao {

    Publisher getPublisher(int publisherId);
    List<Publisher> getAllPublishers();
    Publisher addPublisher(Publisher publisher);
    void updatePublisher(Publisher publisher);
    void deletePublisher(int publisherId);

}
