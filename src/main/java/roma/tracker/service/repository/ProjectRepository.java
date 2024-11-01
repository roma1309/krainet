package roma.tracker.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roma.tracker.service.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
}
