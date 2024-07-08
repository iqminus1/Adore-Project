package uz.pdp.adoreproject.payload;

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
public class DistrictDTO implements Serializable {
    private Integer id;
    private Integer regionId;
    private String name;
}