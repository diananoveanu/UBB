package core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author diananoveanu
 */

@MappedSuperclass
@NoArgsConstructor
@Data
public class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;
}

