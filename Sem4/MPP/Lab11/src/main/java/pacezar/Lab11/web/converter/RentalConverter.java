package pacezar.Lab11.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pacezar.Lab11.core.model.Rental;
import pacezar.Lab11.web.dto.RentalDto;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDto> {

    private static final Logger log = LoggerFactory.getLogger(
            RentalConverter.class);


    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        Rental rental = new Rental(dto.getFilmId(), dto.getCustomerId());
        rental.setId(dto.getId());
        return rental;
    }


    @Override
    public RentalDto convertModelToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto(
                rental.getFilmId(),
                rental.getCustomerId()
        );
        rentalDto.setId(rental.getId());
        return rentalDto;
    }
}

