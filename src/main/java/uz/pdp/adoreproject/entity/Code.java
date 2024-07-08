package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Code extends AbsIntEntity {
    private String code;
    private Integer attempt;
    private Integer userId;
    private String email;

    public Code(String code, Integer attempt, Integer userId) {
        this.code = code;
        this.attempt = attempt;
        this.userId = userId;
    }
}
