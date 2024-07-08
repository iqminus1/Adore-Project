package uz.pdp.adoreproject.entity.template;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@MappedSuperclass
@EnableJpaAuditing
public abstract class AbsIntEntity extends AbsDateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean deleted;
}
