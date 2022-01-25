package com.rgumennyi.dmpc.controllers;

import com.rgumennyi.dmpc.repository.entity.Customer;
import com.rgumennyi.dmpc.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(description = "Customer API Controller", name = "Customer API")
public class CustomerController {
    private final CustomerRepository repository;

    @Operation(summary = "List customers", description = "Retrieve a list of all customers")
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Operation(summary = "Get single customer", description = "Get single customer record by id")
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Customer> customer = repository.findById(id);
        return customer
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Create customer", description = "Create a new customer records")
    @PutMapping("/customer")
    public ResponseEntity<?> create(@RequestBody @Valid Customer customer) {
        try {
            Customer saved = repository.save(customer);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Delete customer", description = "Delete customer record by id")
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            repository.deleteById(id);

            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
