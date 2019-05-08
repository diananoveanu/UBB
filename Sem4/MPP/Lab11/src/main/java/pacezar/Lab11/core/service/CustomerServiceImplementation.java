package pacezar.Lab11.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pacezar.Lab11.core.model.Customer;
import pacezar.Lab11.core.model.Film;
import pacezar.Lab11.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pacezar.Lab11.core.repository.FilmRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author diananoveanu
 */

@Service
public class CustomerServiceImplementation implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(
            FilmServiceImplementation.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FilmRepository filmRepository;


    @Override
    public Film getFilm(Long f) {
        return this.filmRepository.findById(f).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        log.trace("findAll --- method entered");

        List<Customer> customers = customerRepository.findAll();

        log.trace("findAll: customers={}", customers);

        return customers;
    }

    @Override
    public Customer findOne(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long customerId, String name) {
        log.trace("updateCustomer");

        Optional<Customer> customer = customerRepository.findById(customerId);

        customer.ifPresent(f -> {
            f.setName(name);
        });

        log.trace("updateStudent: student={}", customer.get());

        return customer.orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        log.trace("delete: id={}", id);

        customerRepository.deleteById(id);

        log.trace("delete --- method finished");
    }

    @Override
    public void rentFilm(Film f, Customer c) {
        //try {
        this.customerRepository.getOne(c.getId()).addFilm(f);
        //}
        //catch (Exception e) {
        //    e.printStackTrace();
        // }
    }

    @Override
    public void deleteRental(Long filmId, Long customerId) {
        Set<Film> films = this.customerRepository.getOne(customerId).getFilms();
        Set<Film> filmz = new HashSet<>();
        for (Film f : films) {
            if (filmId != f.getId()) {
                filmz.add(f);
            }
        }
        this.customerRepository.getOne(customerId).setFilms(filmz);
    }
}


