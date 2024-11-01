package roma.tracker.service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import roma.tracker.service.converter.RecordConverter;
import roma.tracker.service.dto.RecordDto;
import roma.tracker.service.dto.UpdateRecordDto;
import roma.tracker.service.entity.RecordEntity;
import roma.tracker.service.entity.TaskEntity;
import roma.tracker.service.exceptions.ApiException;
import roma.tracker.service.exceptions.TaskNotFoundException;
import roma.tracker.service.repository.RecordRepository;
import roma.tracker.service.repository.TaskRepository;
import roma.tracker.service.service.RecordService;

import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {
    private final TaskRepository taskRepo;
    private final RecordRepository recordRepo;

    @Autowired
    public RecordServiceImpl(TaskRepository taskRepo, RecordRepository recordRepo) {
        this.taskRepo = taskRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public RecordEntity createdRecord(RecordDto recordDto, Long taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(HttpStatus.BAD_REQUEST, "wrong id", "task not found"));
        if (recordDto.getStartDateTask().isBefore(LocalDateTime.now()) || recordDto.getDeadlineTask().isBefore(recordDto.getStartDateTask())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "incorrect time", "check date and time");
        }
        try {
            return recordRepo.save(RecordConverter.dtoToEntity(recordDto, taskEntity));
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "wrong task_id", "wrong task id");
        }
    }


    @Override
    public RecordEntity updateRecord(UpdateRecordDto updateRecordDto, Long recordId) {
        RecordEntity recordEntity = recordRepo.findById(recordId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "wrong task_id", "wrong task id"));
        if (updateRecordDto.getDeadlineTask().isBefore(recordEntity.getStartDateTask())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "incorrect time", "check date and time");
        }
        recordEntity.setDeadlineTask(updateRecordDto.getDeadlineTask());
        return recordRepo.save(recordEntity);
    }
}
