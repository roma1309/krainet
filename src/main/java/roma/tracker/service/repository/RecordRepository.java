package roma.tracker.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roma.tracker.service.entity.RecordEntity;
import roma.tracker.service.entity.TaskEntity;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    RecordEntity findByTaskEntity(TaskEntity taskEntity);
}
