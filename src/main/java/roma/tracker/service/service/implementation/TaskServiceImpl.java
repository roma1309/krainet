package roma.tracker.service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import roma.tracker.service.converter.TaskConverter;
import roma.tracker.service.dto.GeneralInfoTask;
import roma.tracker.service.dto.TaskDto;
import roma.tracker.service.entity.ProjectEntity;
import roma.tracker.service.entity.TaskEntity;
import roma.tracker.service.entity.UserEntity;
import roma.tracker.service.entity.enums.Department;
import roma.tracker.service.exceptions.ProjectNotFoundException;
import roma.tracker.service.exceptions.TaskNotFoundException;
import roma.tracker.service.exceptions.UserExistException;
import roma.tracker.service.exceptions.ValidException;
import roma.tracker.service.repository.ProjectRepository;
import roma.tracker.service.repository.TaskRepository;
import roma.tracker.service.repository.UserRepository;
import roma.tracker.service.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;
    private final TaskRepository taskRepo;


    @Autowired
    public TaskServiceImpl(UserRepository userRepos, ProjectRepository projectRepo, TaskRepository taskRepo) {
        this.userRepo = userRepos;
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskEntity createdTask(TaskDto taskDto, Long userId, Long projectId, String department) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserExistException(HttpStatus.BAD_REQUEST, "wrong id", "user not found"));
        ProjectEntity projectEntity = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(HttpStatus.BAD_REQUEST, "wrong id", "project not found"));
        if (Department.getDepartment(department) == null) {
            throw new ValidException(HttpStatus.BAD_REQUEST, "wrong department", "department not found");
        }

        return taskRepo.save(TaskConverter.dtoToEntity(taskDto, userEntity, projectEntity, department));
    }

    @Override
    public List<TaskEntity> findTasksForUser(UserEntity userEntity) {

        return userEntity.getTasksEntity();
    }

    /*
  метод для пользователя который выполнил таску

*/
    @Override
    public TaskEntity updateStatusTask(Long taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(HttpStatus.BAD_REQUEST, "wrong id", "task not found"));
        taskEntity.setCompleted(true);
        taskEntity.getRecordEntity().setDateToComplete(LocalDateTime.now());
        return taskRepo.save(taskEntity);
    }

    @Override
    public List<GeneralInfoTask> findGeneralInfoTasksForUser(UserEntity userEntity) {
        return userEntity.getTasksEntity().stream().map(TaskConverter::createGeneralInfo).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
       TaskEntity taskEntity = taskRepo.findById(id)
               .orElseThrow(() -> new TaskNotFoundException(HttpStatus.BAD_REQUEST, "wrong id", "task not found"));
    taskRepo.delete(taskEntity);
    }
}
