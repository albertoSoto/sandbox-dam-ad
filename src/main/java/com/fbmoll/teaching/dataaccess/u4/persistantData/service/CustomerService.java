package com.fbmoll.teaching.dataaccess.u4.persistantData.service;

import com.fbmoll.teaching.dataaccess.u2.filePersistence.helper.FileDataSingleton;
import com.fbmoll.teaching.dataaccess.u4.persistantData.data.Customer;
import com.fbmoll.teaching.dataaccess.u4.persistantData.data.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final CustomerRepository repository;
    private Logger log = LoggerFactory.getLogger(getClass());

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void initDB() {
        try {
/*            String empty = null;
            empty.equals("nulaquen");*/
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));
        } catch (Exception e) {
            log.error("ojo al loro!",e);
        }
    }

    public CustomerRepository getRepository() {
        return repository;
    }
}
