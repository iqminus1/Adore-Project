package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class OrderCrudDTO implements Serializable {
    private LocalDateTime date;
    @NotNull(message = "Null")
    private Integer restaurantId;

    private Integer userId;

    @NotNull(message = "Null")
    private StatusEnum statusEnum;

    @NotNull(message = "Null")
    private WorkTimeEnum workTimeEnum;
}