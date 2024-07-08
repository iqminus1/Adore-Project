package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.District}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictCrudDTO implements Serializable {
    @NotNull
    private Integer regionId;
    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String name;
}