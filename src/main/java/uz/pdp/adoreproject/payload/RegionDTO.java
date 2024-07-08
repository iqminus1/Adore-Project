package uz.pdp.adoreproject.payload;

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
public class RegionDTO implements Serializable {
    private Integer id;
    private String name;
}