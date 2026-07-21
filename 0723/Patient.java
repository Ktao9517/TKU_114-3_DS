public class Patient {
    private int number;
    private String name;
    private String department;

    public Patient(int number, String name, String department) {
        this.number = number;
        this.name = name.trim();
        this.department = department.trim();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "號碼:" + number + " | " + name + " | 科別:" + department;
    }
}