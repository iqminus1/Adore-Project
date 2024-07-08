package uz.pdp.adoreproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.adoreproject.enums.StatusEnum;
import uz.pdp.adoreproject.enums.WorkTimeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Integer id;
    private LocalDateTime date;
    private Integer restaurantId;
    private Integer userId;
    private StatusEnum statusEnum;
    private WorkTimeEnum workTimeEnum;
}