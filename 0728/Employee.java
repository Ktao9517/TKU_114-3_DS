public class Employee {
    private String id;
    private String name;
    private String department;
    private String extension;

    public Employee(String id, String name, String department, String extension) {
        this.id = id.trim();
        this.name = name.trim();
        this.department = department.trim();
        this.extension = extension.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getExtension() { return extension; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | 分機:" + extension;
    }
}
