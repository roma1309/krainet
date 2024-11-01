package roma.tracker.service.dto;

import java.time.LocalDateTime;

public class GeneralInfoTask {
    private String name;
    private String description;
    private String department;
    private boolean isCompleted;

    private LocalDateTime startDateTask;

    private LocalDateTime deadlineTask;

    private LocalDateTime dateToComplete;

    public GeneralInfoTask(String name, String description,
                           String department, boolean isCompleted,
                           LocalDateTime startDateTask, LocalDateTime deadlineTask, LocalDateTime dateToComplete) {
        this.name = name;
        this.description = description;
        this.department = department;
        this.isCompleted = isCompleted;
        this.startDateTask = startDateTask;
        this.deadlineTask = deadlineTask;
        this.dateToComplete = dateToComplete;
    }

    public GeneralInfoTask(String name, String description, String department, boolean isCompleted, LocalDateTime startDateTask, LocalDateTime deadlineTask) {
        this.name = name;
        this.description = description;
        this.department = department;
        this.isCompleted = isCompleted;
        this.startDateTask = startDateTask;
        this.deadlineTask = deadlineTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateToComplete() {
        return dateToComplete;
    }

    public void setDateToComplete(LocalDateTime dateToComplete) {
        this.dateToComplete = dateToComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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

}
