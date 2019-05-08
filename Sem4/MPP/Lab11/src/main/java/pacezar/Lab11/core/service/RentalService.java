package pacezar.Lab11.core.service;

import pacezar.Lab11.core.model.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> findAll();

    Rental updateRental(Long filmId, Long customerId);

    void saveRental(Rental rental);

    void deleteRental(Long id);

}
