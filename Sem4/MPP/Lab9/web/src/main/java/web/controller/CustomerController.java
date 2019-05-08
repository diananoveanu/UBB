package web.controller;

import core.model.Customer;
import core.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.CustomerConverter;
import web.dto.CustomerDto;
import web.dto.CustomersDto;

import java.util.List;


/**
 * author: radu
 */

@RestController
public class CustomerController {
    private static final Logger log =
            LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    CustomersDto getAllCustomers() {
        log.trace("getAllCustomers --- method entered");

        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> dtos = customerConverter.convertModelsToDtos(customers);
        CustomersDto result = new CustomersDto(dtos);

        log.trace("getAllCustomers: result={}", result);

        return result;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    CustomerDto saveCustomer(@RequestBody CustomerDto dto) {
        log.trace("saveCustomer: web.dto={}", dto);

        Customer saved = this.customerService.saveCustomer(
                customerConverter.convertDtoToModel(dto)
        );
        CustomerDto result = customerConverter.convertModelToDto(saved);

        log.trace("saveCustomer: result={}", result);

        return result;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    CustomerDto updateCustomer(@PathVariable Long id,
                             @RequestBody CustomerDto dto) {
        log.trace("updateCustomer: id={},web.dto={}", id, dto);

        Customer update = customerService.updateCustomer(
                id,
                customerConverter.convertDtoToModel(dto));
        CustomerDto result = customerConverter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        log.trace("deleteCustomer: id={}", id);

        customerService.deleteCustomer(id);

        log.trace("deleteCustomer --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    CustomerDto findCustomer(@PathVariable Long id) {
        log.trace("findCustomer --method entered id = {}", id);

        //*insert into customers values ( customer.id(),customer.name())*

        if (customerService.findById(id)==null)
            return new CustomerDto();

        Customer customer = customerService.findById(id).get();
        CustomerDto customerDto = customerConverter.convertModelToDto(customer);

        log.trace("findCustomer --method exit res = {}", customerDto);

        return customerDto;
    }
}
