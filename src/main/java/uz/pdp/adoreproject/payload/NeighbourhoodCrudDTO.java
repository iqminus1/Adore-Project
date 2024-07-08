package uz.pdp.adoreproject.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.Neighbourhood}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeighbourhoodCrudDTO implements Serializable {
    @NotNull
    private Integer districtId;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String name;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String street;

    @NotNull(message = "Null")
    @NotBlank(message = "Blank")
    private String houseNumber;
}