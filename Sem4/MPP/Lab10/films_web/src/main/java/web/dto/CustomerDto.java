package web.dto;

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
public class CustomerDto extends BaseDto{
    private String name;

    @Override
    public String toString() {
        return "CustomerDto{" +
               "name='" + name + '\'' +
               "} " + super.toString();
    }
}
