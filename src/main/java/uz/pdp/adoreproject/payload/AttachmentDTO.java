package uz.pdp.adoreproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.adoreproject.entity.Attachment;

import java.io.Serializable;

/**
 * DTO for {@link Attachment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO implements Serializable {
    private Integer id;
    private String originalName;
    private String name;
    private String path;
    private String contentType;
    private Long size;
}