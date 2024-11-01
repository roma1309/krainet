package roma.tracker.service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import roma.tracker.service.converter.ProjectConverter;
import roma.tracker.service.dto.ProjectDto;
import roma.tracker.service.entity.ProjectEntity;
import roma.tracker.service.exceptions.ProjectNotFoundException;
import roma.tracker.service.repository.ProjectRepository;
import roma.tracker.service.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public ProjectEntity createProject(ProjectDto projectDto) {
        return projectRepo.save(ProjectConverter.dtoToEntity(projectDto));
    }

    @Override
    public void deleteById(Long id) {
        ProjectEntity projectEntity = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(HttpStatus.BAD_REQUEST, "wrong id", "project not found"));
        projectRepo.delete(projectEntity);
    }

}
