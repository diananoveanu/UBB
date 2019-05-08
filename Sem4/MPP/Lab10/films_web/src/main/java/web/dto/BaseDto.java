package web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author diananoveanu
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
