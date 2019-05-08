package pacezar.Lab11.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "Film")
public class Film extends BaseEntity<Long> {
    private String title;
    private String director;
    private Integer releaseYear;

//    public Film(String title, String director, Integer releaseYear){
//        this.title=title;
//        this.director=director;
//        this.releaseYear=releaseYear;
//    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Rental",
            joinColumns = {@JoinColumn(name = "filmId")},
            inverseJoinColumns = {@JoinColumn(name = "customerId")}
    )
    Set<Customer> customers = new HashSet<>();

    @Override
    public String toString() {
        return "Film{" +
                "name='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseYear=" + releaseYear +
                "} " + super.toString();
    }
    public void addCustomer(Customer c){
        customers.add(c);
    }
}
