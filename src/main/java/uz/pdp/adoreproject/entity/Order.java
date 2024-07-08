package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;
import uz.pdp.adoreproject.enums.StatusEnum;
import uz.pdp.adoreproject.enums.WorkTimeEnum;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "orders")
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update orders set deleted = true where id = ?"))
public class Order extends AbsIntEntity {

    private LocalDateTime date;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Enumerated(EnumType.STRING)
    private WorkTimeEnum workTime;
}
