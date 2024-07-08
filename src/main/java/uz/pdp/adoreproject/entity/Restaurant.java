package uz.pdp.adoreproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;
import uz.pdp.adoreproject.enums.ServiceEnum;
import uz.pdp.adoreproject.enums.WorkTimeEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update restaurant set deleted = true where id = ?"))
public class Restaurant extends AbsIntEntity {
    private String name;

    private String price;

    private String description;

    private String phoneNumber;

    @OneToOne
    private Neighbourhood neighbourhood;

    private Integer peopleSize;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Enumerated(EnumType.STRING)
    private List<WorkTimeEnum> workTimes;


    @JdbcTypeCode(SqlTypes.ARRAY)
    @Enumerated(EnumType.STRING)
    private List<ServiceEnum> services;


    @ManyToMany
    @ToString.Exclude
    private List<Attachment> attachments;
}
