package com.rgumennyi.dmpc.controllers;

import com.rgumennyi.dmpc.repository.CustomerRepository;
import com.rgumennyi.dmpc.repository.entity.Customer;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void cleanUp() {
        repository.findAll().forEach(repository::delete);
    }

    @Test
    void getAllCustomers_empty() {
        List<Customer> customers = customerController.getAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.isEmpty());
    }

    @Test
    void getAllCustomers_notEmpty() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        repository.save(customer);

        List<Customer> customers = customerController.getAllCustomers();
        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals(customer.getFirstName(), customers.get(0).getFirstName());
        assertEquals(customer.getLastName(), customers.get(0).getLastName());
        assertEquals(customer.getEmail(), customers.get(0).getEmail());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        Customer saved = repository.save(customer);

        ResponseEntity<Customer> response = customerController.getCustomerById(saved.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saved, response.getBody());
    }

    @Test
    void getCustomerById_badRequest() {
        ResponseEntity<Customer> response = customerController.getCustomerById(null);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getCustomerById_notFound() {
        ResponseEntity<Customer> response = customerController.getCustomerById(-1);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        ResponseEntity<?> response = customerController.create(customer);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void delete() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        Customer saved = repository.save(customer);

        ResponseEntity<?> response = customerController.delete(saved.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}