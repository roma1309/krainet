package roma.tracker.service.converter;

import roma.tracker.service.dto.GeneralInfoTask;
import roma.tracker.service.dto.TaskDto;
import roma.tracker.service.entity.ProjectEntity;
import roma.tracker.service.entity.TaskEntity;
import roma.tracker.service.entity.UserEntity;
import roma.tracker.service.entity.enums.Department;

public class TaskConverter {

    public static TaskEntity dtoToEntity(TaskDto taskDto, UserEntity userEntity, ProjectEntity projectEntity, String department) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setName(taskDto.getName());
        taskEntity.setProjectEntity(projectEntity);
        taskEntity.setUserEntity(userEntity);
        taskEntity.setDepartment(Department.getDepartment(department));
        return taskEntity;
    }

    public static TaskDto entityToDto(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setName(taskEntity.getName());

        return taskDto;
    }

    public static GeneralInfoTask createGeneralInfo(TaskEntity taskEntity) {
        if (taskEntity.isCompleted()) {
            return new GeneralInfoTask(taskEntity.getName(), taskEntity.getDescription(),
                    taskEntity.getDescription(), taskEntity.isCompleted(), taskEntity.getRecordEntity().getStartDateTask(),
                    taskEntity.getRecordEntity().getDeadlineTask(), taskEntity.getRecordEntity().getDateToComplete());
        }
        return new GeneralInfoTask(taskEntity.getName(), taskEntity.getDescription(),
                taskEntity.getDescription(), taskEntity.isCompleted(), taskEntity.getRecordEntity().getStartDateTask(),
                taskEntity.getRecordEntity().getDeadlineTask());
    }
}
