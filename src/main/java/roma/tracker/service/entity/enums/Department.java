package roma.tracker.service.entity.enums;

public enum Department {
    QA, BACKEND, FRONTEND, MANAGER;

    public static Department getDepartment(String departmentStr) {
        for (Department department : Department.values()) {
            if (department.name().equals(departmentStr)) {
                return department;
            }
        }
        return null;
    }
}
