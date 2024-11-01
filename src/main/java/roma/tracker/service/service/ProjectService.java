package roma.tracker.service.service;

import roma.tracker.service.dto.ProjectDto;
import roma.tracker.service.entity.ProjectEntity;

public interface ProjectService {

    public ProjectEntity createProject(ProjectDto projectDto);

    public void deleteById(Long id);

}
