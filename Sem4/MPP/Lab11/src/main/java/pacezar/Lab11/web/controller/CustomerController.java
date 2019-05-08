package pacezar.Lab11.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pacezar.Lab11.core.model.Customer;
import pacezar.Lab11.core.model.Film;
import pacezar.Lab11.core.model.Rental;
import pacezar.Lab11.core.service.CustomerService;
import pacezar.Lab11.core.service.CustomerService;
import pacezar.Lab11.web.converter.CustomerConverter;
import pacezar.Lab11.web.converter.RentalConverter;
import pacezar.Lab11.web.dto.CustomerDto;
import pacezar.Lab11.web.converter.CustomerConverter;
import pacezar.Lab11.web.dto.CustomerDto;
import pacezar.Lab11.web.dto.RentalDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author diananoveanu
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private RentalConverter rentalConverter;


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    CustomerDto saveCustomer(@RequestBody CustomerDto dto) {
        return customerConverter.convertModelToDto(
                customerService.saveCustomer(
                        customerConverter.convertDtoToModel(dto)
                ));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public RentalDto saveRental(@RequestBody RentalDto dto) {
//        try {
        customerService.rentFilm(customerService.getFilm(dto.getFilmId()), customerService.findOne(dto.getCustomerId()));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        return rentalConverter.convertModelToDto(new Rental(dto.getFilmId(),dto.getCustomerId()));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public Set<RentalDto> getRentals() {
        List<Customer> customers = customerService.findAll();
        Set<Rental> rentals = new HashSet<>();
        for (Customer i : customers){
            for (Film m : i.getFilms()){
                rentals.add(new Rental(i.getId(),m.getId()));
            }
        }
        return rentalConverter.convertModelsToDtos(rentals);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerDto> getCustomers() {


        List<Customer> students = customerService.findAll();


        return new ArrayList<>(customerConverter.convertModelsToDtos(students));
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    public CustomerDto updateCustomer(
            @PathVariable final Long customerId,
            @RequestBody final CustomerDto customerDto) {

        Customer customer = customerService.updateCustomer(customerId, customerDto.getName());
        CustomerDto result = customerConverter.convertModelToDto(customer);
        return result;
    }


    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
