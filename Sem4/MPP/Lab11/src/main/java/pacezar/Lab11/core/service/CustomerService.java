package pacezar.Lab11.core.service;

import pacezar.Lab11.core.model.Customer;
import pacezar.Lab11.core.model.Film;

import java.util.List;

/**
 * @author diananoveanu
 */

public interface CustomerService {
    List<Customer> findAll();

    Customer findOne(Long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    Customer updateCustomer(Long id, String name);

    void rentFilm(Film f, Customer c);

    Film getFilm(Long f);

    void deleteRental(Long cid, Long fid);

}
