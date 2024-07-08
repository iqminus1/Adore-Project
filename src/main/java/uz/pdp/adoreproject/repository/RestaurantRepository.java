package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.adoreproject.entity.Restaurant;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}