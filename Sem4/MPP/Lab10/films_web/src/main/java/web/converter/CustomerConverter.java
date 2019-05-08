package web.converter;

import core.model.Customer;
import org.springframework.stereotype.Component;
import web.dto.CustomerDto;

/**
 * @author diananoveanu
 */

@Component
public class CustomerConverter
        extends BaseConverter<Customer, CustomerDto> {
    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer discipline = new Customer(dto.getName());
        discipline.setId(dto.getId());
        return discipline;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        CustomerDto dto = new CustomerDto(customer.getName());
        dto.setId(customer.getId());
        return dto;
    }
}
