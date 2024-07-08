package uz.pdp.adoreproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;
import uz.pdp.adoreproject.enums.PermissionEnum;
import uz.pdp.adoreproject.enums.RoleTypeEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "roles")
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update roles set deleted = true where id = ?"))
public class Role extends AbsIntEntity {
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum roleType;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<PermissionEnum> permissions;
}
