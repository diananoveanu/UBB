package pacezar.Lab11.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author diananoveanu
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalDto extends BaseDto {
    private Long filmId;
    private Long customerId;

    @Override
    public String toString() {
        return "RentalDto{" +
                "filmId='" + filmId + '\'' +
                ", customerId='" + customerId + '\'' +
                "} " + super.toString();
    }
}
