package roma.tracker.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roma.tracker.service.converter.RecordConverter;
import roma.tracker.service.dto.RecordDto;
import roma.tracker.service.dto.UpdateRecordDto;
import roma.tracker.service.entity.RecordEntity;
import roma.tracker.service.service.RecordService;

@RestController
@RequestMapping("/api/record")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping("/create")
    public ResponseEntity<RecordDto> createRecord(@RequestBody RecordDto recordDto,
                                                  @RequestParam(value = "task_id") final String taskId) {
        System.out.println(recordDto.getDeadlineTask());
        RecordEntity recordEntity = recordService.createdRecord(recordDto, Long.parseLong(taskId));
        return new ResponseEntity<>(RecordConverter.entityToDto(recordEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}/update")
    public ResponseEntity<RecordDto> updateRecord(@RequestBody UpdateRecordDto updateRecordDto,
                                                  @PathVariable("recordId") String recordId) {
        RecordEntity recordEntity = recordService.updateRecord(updateRecordDto, Long.parseLong(recordId));
        return new ResponseEntity<>(RecordConverter.entityToDto(recordEntity), HttpStatus.OK);
    }
}
