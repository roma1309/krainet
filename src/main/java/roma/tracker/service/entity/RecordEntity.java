package roma.tracker.service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "records")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date_task")
    private LocalDateTime startDateTask;
    @Column(name = "deadline_task")
    private LocalDateTime deadlineTask;

    @Column(name = "date_to_complete")
    private LocalDateTime dateToComplete;

    @OneToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "id", unique = true)
    private TaskEntity taskEntity;


    public RecordEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTask() {
        return startDateTask;
    }

    public void setStartDateTask(LocalDateTime startDateTask) {
        this.startDateTask = startDateTask;
    }

    public LocalDateTime getDeadlineTask() {
        return deadlineTask;
    }

    public void setDeadlineTask(LocalDateTime deadlineTask) {
        this.deadlineTask = deadlineTask;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public LocalDateTime getDateToComplete() {
        return dateToComplete;
    }

    public void setDateToComplete(LocalDateTime dateToComplete) {
        this.dateToComplete = dateToComplete;
    }
}
