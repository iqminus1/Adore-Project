package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.adoreproject.entity.Role;
import uz.pdp.adoreproject.enums.RoleTypeEnum;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleType(RoleTypeEnum roleType);
}