package com.fbmoll.teaching.dataaccess.nosql;

import com.fbmoll.teaching.dataaccess.u4.persistantData.data.Customer;
import com.fbmoll.teaching.dataaccess.u4.persistantData.data.CustomerRepository;
import com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data.MongoCustomRepository;
import com.fbmoll.teaching.dataaccess.ud5.nonrdbData.data.MongoCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoApplicationTest {
    @Autowired
    MongoCustomRepository repository;

    @Test
    void testRepository(){
        repository.deleteAll();
        // save a couple of customers
        repository.save(new MongoCustomer("Alice", "Smith"));
        repository.save(new MongoCustomer("Bob", "Smith"));
        // fetch all MongoCustomers
        System.out.println("MongoCustomers found with findAll():");
        System.out.println("-------------------------------");
        for (MongoCustomer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (MongoCustomer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
