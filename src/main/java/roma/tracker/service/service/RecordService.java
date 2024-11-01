package roma.tracker.service.service;

import roma.tracker.service.dto.RecordDto;
import roma.tracker.service.dto.UpdateRecordDto;
import roma.tracker.service.entity.RecordEntity;

public interface RecordService {

    public RecordEntity createdRecord(RecordDto recordDto, Long taskId);

    public RecordEntity updateRecord(UpdateRecordDto updateRecordDto, Long recordId);
}
