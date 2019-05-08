package pacezar.Lab11.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pacezar.Lab11.core.model.Film;

import java.util.List;
import java.util.Set;

/**
 * @author diananoveanu
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto extends BaseDto {
    private String name;
    public Set<Film> films;
//
//    public CustomerDto(String name){
//        this.name = name;
//    }

    @Override
    public String toString() {
        return "CustomerDto{" +
               "name='" + name + '\'' +
               "} " + super.toString();
    }
}
