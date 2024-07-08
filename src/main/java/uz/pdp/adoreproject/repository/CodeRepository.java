package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.adoreproject.entity.Code;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {
    Optional<Code> findByUserId(Integer id);

    Optional<Code> findByCode(String code);
}