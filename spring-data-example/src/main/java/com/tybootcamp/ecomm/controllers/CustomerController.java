package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerRepository _customerRepository;

    public CustomerController(CustomerRepository customerRepository){ this._customerRepository = customerRepository; }

    @GetMapping(path = "/get")
    public ResponseEntity<?> getCustomerById(@RequestParam long id){
        try {
            Customer customer = _customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            System.out.println("The customer with id " + id + " = " + customer.toString());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There isn't any customer with this name.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody Customer customer){
        Customer customerEntity = new Customer(customer.getId());
        Profile profile = new Profile(customerEntity, customer.getProfile().getFirstName(), customer.getProfile().getLastName(), customer.getProfile().getGender());
        customerEntity.setProfile(profile);
        customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
        customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
        customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
        customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
        customerEntity = _customerRepository.save(customerEntity);
        return new ResponseEntity<>(customerEntity, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer)
    {
        Customer customerEntity = _customerRepository.findById(customer.getId()).orElse(null);
        if (customerEntity == null)
        {
            return new ResponseEntity<>("This customer doesn't exists.", HttpStatus.NOT_FOUND);
        }
        customerEntity.setId(customer.getId());
        customerEntity.getProfile().setFirstName(customer.getProfile().getFirstName());
        customerEntity.getProfile().setLastName(customer.getProfile().getLastName());
        customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
        customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
        customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
        customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
        customerEntity.getProfile().setGender(customer.getProfile().getGender());
        customerEntity = _customerRepository.save(customerEntity);
        System.out.println("__________________________________________________________________");
        System.out.println("The row of " + customerEntity.toString() + " updated");
        return new ResponseEntity<>("The customer updated", HttpStatus.OK);
    }
}
