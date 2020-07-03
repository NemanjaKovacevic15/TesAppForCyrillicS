package com.cyrillicsoftware.service;

import java.util.List;

import com.cyrillicsoftware.model.Customer;

public interface CustomerService {

    Customer findOne(Long id);

    List<Customer> findAll();

    Customer save(Customer toSave);

    Customer delete(Long id);

}
