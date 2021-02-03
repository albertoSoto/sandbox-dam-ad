package com.fbmoll.teaching.dataaccess.ud5.nonrdbData.service;

import com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data.MongoCustomRepository;
import com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data.MongoCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoCustomerService {
    @Autowired
    MongoCustomRepository repository;

    private void deleteById(String id) {
        repository.deleteById(id);
    }

    private MongoCustomer addCustomer(MongoCustomer customer) {
        return repository.insert(customer);
    }

    private MongoCustomer getCustomerByName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    private List<MongoCustomer> getAllCustomers() {
        return repository.findAll();
    }
}
