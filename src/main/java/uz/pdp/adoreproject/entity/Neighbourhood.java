package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update neighbourhood set deleted = true where id = ?"))
public class Neighbourhood extends AbsIntEntity {
    @ManyToOne
    private District district;

    private String name;

    private String street;

    private String houseNumber;
}
