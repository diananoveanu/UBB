package core.service;

import core.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Optional<Customer> findById(Long id);
}

