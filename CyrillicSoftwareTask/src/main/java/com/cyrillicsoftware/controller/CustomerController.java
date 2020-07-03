package com.cyrillicsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyrillicsoftware.model.Customer;
import com.cyrillicsoftware.service.CustomerService;
import com.cyrillicsoftware.converter.CustomerDtoToCustomer;
import com.cyrillicsoftware.converter.CustomerToCustomerDTO;
import com.cyrillicsoftware.dto.CustomerDTO;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerToCustomerDTO toDto;
    @Autowired
    private CustomerDtoToCustomer toUser;

    // Get All Customers
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<CustomerDTO>> getAll() {

        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(toDto.convert(customers), HttpStatus.OK);
    }

    // Get One Customer
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<CustomerDTO> getOne(@PathVariable Long id) {

        Customer customer = customerService.findOne(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(customer), HttpStatus.OK);
    }

    // Add Customer
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<CustomerDTO> add(@RequestBody CustomerDTO toSave) {

        Customer saved = customerService.save(toUser.convert(toSave));

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }

    // Delete Customer
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<CustomerDTO> delete(@PathVariable Long id) {

        Customer deleted = customerService.delete(id);

        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
    }

    // Edit Customer
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<CustomerDTO> edit(@PathVariable Long id, @RequestBody CustomerDTO toEdit) {

        if (!Objects.equals(id, toEdit.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer edited = customerService.save(toUser.convert(toEdit));

        return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
    }

}
