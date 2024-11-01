package roma.tracker.service.service;

import roma.tracker.service.dto.GeneralInfoTask;
import roma.tracker.service.dto.TaskDto;
import roma.tracker.service.entity.TaskEntity;
import roma.tracker.service.entity.UserEntity;

import java.util.List;

public interface TaskService {
    public TaskEntity createdTask(TaskDto taskDto, Long userId, Long projectId, String department);

    public List<TaskEntity> findTasksForUser(UserEntity userEntity);

    public TaskEntity updateStatusTask(Long taskId);

    public List<GeneralInfoTask> findGeneralInfoTasksForUser(UserEntity userEntity);

    public void deleteById(Long id);
}
