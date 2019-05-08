package web.controller;

import core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.converter.CustomerConverter;
import web.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diananoveanu
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    List<CustomerDto> getAllCustomers() {
        return new ArrayList<>(customerConverter.convertModelsToDtos(
                customerService.getAllCustomers()
        ));
    }


}
