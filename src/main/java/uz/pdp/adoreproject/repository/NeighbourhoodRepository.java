package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.adoreproject.entity.Neighbourhood;

import java.util.Optional;

@Repository
public interface NeighbourhoodRepository extends JpaRepository<Neighbourhood, Integer> {
    Optional<Neighbourhood> findByNameAndDistrictIdAndHouseNumberAndStreet(String neighbourhoodName, Integer id, String neighbourhoodHouseNumber, String neighbourhoodStreet);
}