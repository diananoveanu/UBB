package pacezar.Lab11.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pacezar.Lab11.core.model.Rental;
import pacezar.Lab11.core.service.FilmService;
import pacezar.Lab11.core.service.RentalService;
import pacezar.Lab11.web.converter.RentalConverter;
import pacezar.Lab11.web.dto.RentalDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author diananoveanu
 */

@RestController
public class RentalController {

    private static final Logger log = LoggerFactory.getLogger(
            RentalController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalConverter rentalConverter;


    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public List<RentalDto> getRentals() {

        List<Rental> rentals = rentalService.findAll();
        List<RentalDto> rents = new ArrayList<>();
        for(Rental r: rentals){
            rents.add(rentalConverter.convertModelToDto(r));
        }
        return rents;
    }

    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.PUT)
    public RentalDto updateRental(
            @PathVariable final Long rentalId,
            @RequestBody final RentalDto rentalDto) {


        Rental rental = rentalService.updateRental(rentalDto.getFilmId(),rentalDto.getCustomerId());

        RentalDto result = rentalConverter.convertModelToDto(rental);

        log.trace("updateRental: result={}", result);

        return result;
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    RentalDto saveRental(@RequestBody RentalDto dto) {
        rentalService.saveRental(rentalConverter.convertDtoToModel(dto));
        return dto;
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    public void deleteRental(@PathVariable Long id) {
        log.trace("deleteRental: id={}", id);

        rentalService.deleteRental(id);

        log.trace("deleteRental --- method finished");
    }
}
