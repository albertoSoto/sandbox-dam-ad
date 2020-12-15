package com.fbmoll.teaching.dataaccess.persistantData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.fbmoll.teaching.dataaccess.persistantData
 * Class CustomerService
 * 15/12/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
@Service
public class CustomerService {
    final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void initDB(){
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
    }

    public CustomerRepository getRepository() {
        return repository;
    }
}
