package utils;

public enum EmployeePermission {
    STAFF("staff", "Nhân viên", 1),
    MANAGER("manager", "Quản lý", 2),
    INACTIVE("inactive", "Nghỉ việc", 0);

    private String id, name;
    private int priority;

    EmployeePermission(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public static EmployeePermission getById(String id) {
        for (EmployeePermission e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return STAFF;
    }

    public static EmployeePermission getByName(String name) {
        for (EmployeePermission e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return STAFF;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
   
    public int compare(EmployeePermission other) {
        return this.priority - other.priority;
    }

}
