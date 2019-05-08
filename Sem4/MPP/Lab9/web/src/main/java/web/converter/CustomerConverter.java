package web.converter;

import web.dto.CustomerDto;
import core.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {
    @Override
    public Customer convertDtoToModel(CustomerDto dto) {
        Customer customer = Customer.builder()
                .name(dto.getName())
                .build();
        customer.setId(dto.getId());
        return customer;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {
        CustomerDto dto = CustomerDto.builder()
                .name(customer.getName())
                .build();
        dto.setId(customer.getId());
        return dto;
    }
}
