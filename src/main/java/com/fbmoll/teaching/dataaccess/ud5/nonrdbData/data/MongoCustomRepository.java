package com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoCustomRepository extends MongoRepository<MongoCustomer, String> {

    public MongoCustomer findByFirstName(String firstName);

    public List<MongoCustomer> findByLastName(String lastName);

}