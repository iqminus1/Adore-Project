package uz.pdp.adoreproject.payload;

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
public class NeighbourhoodDTO implements Serializable {
    private Integer id;
    private Integer districtId;
    private String name;
    private String street;
    private String houseNumber;
}