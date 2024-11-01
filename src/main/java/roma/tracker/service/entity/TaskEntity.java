package roma.tracker.service.entity;

import jakarta.persistence.*;
import roma.tracker.service.entity.enums.Department;

/*
 Сущность задач, которая зависима от пользователя, проекта и записи.
 У пользователя и проекта может  быть много задач
 isCompleted флаг, когда пользователь завершил работу, данное свойство меняется на истинну и в сущности записи сохраняется время выполнения таски
*/

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(mappedBy = "taskEntity", cascade = CascadeType.ALL)
    private RecordEntity recordEntity;
    @Column(name = "is_completed")
    private boolean isCompleted;


    public TaskEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public RecordEntity getRecordEntity() {
        return recordEntity;
    }

    public void setRecordEntity(RecordEntity recordEntity) {
        this.recordEntity = recordEntity;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
