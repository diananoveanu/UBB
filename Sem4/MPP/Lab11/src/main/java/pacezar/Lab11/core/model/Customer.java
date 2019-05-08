package pacezar.Lab11.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: diananoveanu
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name="Customer")
public class Customer extends BaseEntity<Long> {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Rental",
            joinColumns = { @JoinColumn(name = "customerId") },
            inverseJoinColumns = { @JoinColumn(name = "filmId") }
    )
    Set<Film> films = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
               "title='" + name + '\'' +
               "} " + super.toString();
    }


    public void addFilm(Film m){
        films.add(m);
    }
}
