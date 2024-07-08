package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Comment extends AbsIntEntity {
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;
}
