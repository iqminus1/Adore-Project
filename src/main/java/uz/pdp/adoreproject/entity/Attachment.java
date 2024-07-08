package uz.pdp.adoreproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update attachment set deleted = true where id = ?"))
public class Attachment extends AbsIntEntity {
    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;
}
