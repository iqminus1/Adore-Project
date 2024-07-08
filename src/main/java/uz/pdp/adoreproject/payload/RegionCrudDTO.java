package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.Region}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCrudDTO implements Serializable {

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String name;
}