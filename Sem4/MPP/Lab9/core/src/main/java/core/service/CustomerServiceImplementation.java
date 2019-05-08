package core.service;

import core.model.Customer;
import core.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(
            CustomerServiceImplementation.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        log.trace("getAllCustomers --- method entered");

        List<Customer> result = customerRepository.findAll();

        log.trace("getAllCustomers: result={}", result);

        return result;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.trace("saveCustomer: film={}", customer);

        Customer savedCustomer = this.customerRepository.save(customer);
        customerRepository.save(customer);

        log.trace("saveCustomer --- method finished");
        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        log.trace("update: id={}, customer={}", id, customer);

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer result = optionalCustomer.orElse(customer);

        log.trace("updateFilm: result={}", result);

        return result;
    }

    @Override
    public void deleteCustomer(Long id) {
        log.trace("delete: id={}", id);

        customerRepository.deleteById(id);

        log.trace("delete --- method finished");
    }

    @Override
    public Optional<Customer> findById(Long id) {

        log.trace("findById --method entered id = {}", id);

        return customerRepository.findById(id);
    }
}
