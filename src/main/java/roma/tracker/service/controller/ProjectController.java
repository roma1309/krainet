package roma.tracker.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roma.tracker.service.converter.ProjectConverter;
import roma.tracker.service.dto.ProjectDto;
import roma.tracker.service.entity.ProjectEntity;
import roma.tracker.service.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.createProject(projectDto);
        return new ResponseEntity<>(ProjectConverter.entityToDto(projectEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}/delete")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("projectId") String projectId) {
        projectService.deleteById(Long.parseLong(projectId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
