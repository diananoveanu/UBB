package web.dto;

import lombok.*;

/**
 * @author diananoveanu
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class CustomerDto extends BaseDto {
    private String name;
}
