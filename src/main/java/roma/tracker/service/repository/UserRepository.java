package roma.tracker.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roma.tracker.service.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

}
