package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.adoreproject.entity.District;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Optional<District> findByNameAndRegionId(String name, Integer id);
}