package roma.tracker.service.converter;

import roma.tracker.service.dto.ProjectDto;
import roma.tracker.service.entity.ProjectEntity;


public class ProjectConverter {

    public static ProjectEntity dtoToEntity(ProjectDto projectDto) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectDto.getName());

        return projectEntity;
    }

    public static ProjectDto entityToDto(ProjectEntity projectEntity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(projectEntity.getName());
        return projectDto;
    }
}
