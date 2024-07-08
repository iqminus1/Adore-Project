package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update district set deleted = true where id = ?"))
public class District extends AbsIntEntity {
    @ManyToOne
    private Region region;

    private String name;

    @ToString.Exclude
    @OneToMany
    private List<Neighbourhood> neighbourhoods;

    public District(Region region, String name) {
        this.region = region;
        this.name = name;
    }
}
