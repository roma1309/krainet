package roma.tracker.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roma.tracker.service.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
