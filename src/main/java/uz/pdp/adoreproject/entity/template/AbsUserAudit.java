package uz.pdp.adoreproject.entity.template;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@MappedSuperclass
public class AbsUserAudit {
    @LastModifiedBy
    private Integer updateByUser;

    @CreatedBy
    private Integer createByUser;
}
