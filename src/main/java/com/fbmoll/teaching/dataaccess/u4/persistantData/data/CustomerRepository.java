package com.fbmoll.teaching.dataaccess.u4.persistantData.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess.persistantData
 * Class CustomerRepository
 * 15/12/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}