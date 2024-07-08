package uz.pdp.adoreproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.adoreproject.entity.Comment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCrudDTO implements Serializable {
    private String text;
    private Integer restaurantId;
}