package roma.tracker.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roma.tracker.service.converter.TaskConverter;
import roma.tracker.service.dto.GeneralInfoTask;
import roma.tracker.service.dto.TaskDto;
import roma.tracker.service.entity.TaskEntity;
import roma.tracker.service.service.TaskService;
import roma.tracker.service.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto,
                                              @RequestParam(value = "user_id") final String userId,
                                              @RequestParam(value = "project_id") final String projectId,
                                              @RequestParam(value = "department") final String department) {
        TaskEntity taskEntity = taskService.createdTask(taskDto, Long.parseLong(userId), Long.parseLong(projectId), department);
        return new ResponseEntity<>(TaskConverter.entityToDto(taskEntity), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getUserTasks(Principal principal) {

        List<TaskDto> taskDtoList = taskService.findTasksForUser(userService.getUserByPrincipal(principal))
                .stream().map(TaskConverter::entityToDto).collect(Collectors.toList());
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/update/complete")
    public ResponseEntity<TaskDto> updateCompleted(@PathVariable("taskId") String taskId) {
        TaskEntity taskEntity = taskService.updateStatusTask(Long.parseLong(taskId));
        return new ResponseEntity<>(TaskConverter.entityToDto(taskEntity), HttpStatus.OK);
    }

    @GetMapping("/general")
    public ResponseEntity<List<GeneralInfoTask>> getUserGeneralInfoTasks(Principal principal) {
        return new ResponseEntity<>(taskService.findGeneralInfoTasksForUser(userService.getUserByPrincipal(principal)), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}/delete")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("taskId") String taskId) {
        taskService.deleteById(Long.parseLong(taskId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
