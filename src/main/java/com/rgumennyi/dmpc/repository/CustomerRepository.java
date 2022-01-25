package com.rgumennyi.dmpc.repository;

import com.rgumennyi.dmpc.repository.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();
}
