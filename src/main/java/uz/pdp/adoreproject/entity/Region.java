package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update region set deleted = true where id = ?"))
public class Region extends AbsIntEntity {
    private String name;

    @OneToMany(mappedBy = "region")
    @ToString.Exclude
    private List<District> districts;

    public Region(String name) {
        this.name = name;
    }
}
