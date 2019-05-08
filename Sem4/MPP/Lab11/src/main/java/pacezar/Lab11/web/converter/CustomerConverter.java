package pacezar.Lab11.web.converter;


import org.springframework.stereotype.Component;
import pacezar.Lab11.core.model.Customer;
import pacezar.Lab11.web.dto.CustomerDto;

/**
 * @author diananoveanu
 */

@Component
public class CustomerConverter
        extends BaseConverter<Customer, CustomerDto> {
    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer customer = new Customer(dto.getName(),dto.getFilms());
        customer.setId(dto.getId());
        return customer;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        CustomerDto dto = new CustomerDto(customer.getName(),customer.getFilms());
        dto.setId(customer.getId());
        return dto;
    }
}
