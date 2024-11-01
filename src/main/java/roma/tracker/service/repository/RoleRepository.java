package roma.tracker.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roma.tracker.service.entity.RoleEntity;
import roma.tracker.service.entity.enums.RoleEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
