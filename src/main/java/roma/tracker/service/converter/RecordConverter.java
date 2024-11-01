package roma.tracker.service.converter;


import roma.tracker.service.dto.RecordDto;
import roma.tracker.service.entity.RecordEntity;
import roma.tracker.service.entity.TaskEntity;

public class RecordConverter {

    public static RecordEntity dtoToEntity(RecordDto recordDto, TaskEntity taskEntity) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setStartDateTask(recordDto.getStartDateTask());
        recordEntity.setDeadlineTask(recordDto.getDeadlineTask());
        recordEntity.setTaskEntity(taskEntity);
        return recordEntity;
    }

    public static RecordDto entityToDto(RecordEntity recordEntity) {
        RecordDto recordDto = new RecordDto();
        recordDto.setDeadlineTask(recordEntity.getDeadlineTask());
        recordDto.setStartDateTask(recordEntity.getStartDateTask());
        return recordDto;
    }
}
