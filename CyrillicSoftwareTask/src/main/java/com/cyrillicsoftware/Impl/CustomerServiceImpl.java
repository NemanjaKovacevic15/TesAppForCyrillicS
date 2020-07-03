package com.cyrillicsoftware.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyrillicsoftware.model.Customer;
import com.cyrillicsoftware.repository.CustomerRepository;
import com.cyrillicsoftware.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer toSave) {
        return customerRepository.save(toSave);
    }

    @Override
    public Customer delete(Long id) {
        Customer toDelete = customerRepository.getOne(id);
        customerRepository.delete(toDelete);
        return toDelete;
    }

}
