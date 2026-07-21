public class Equipment {
    private String code;
    private String name;
    private boolean available;

    public Equipment(String code, String name) {
        this.code = code.trim();
        this.name = name.trim();
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void borrow() {
        if (available) {
            available = false;
        }
    }

    public void returnEquipment() {
        available = true;
    }

    @Override
    public String toString() {
        String status = available ? "可借用" : "已借出";
        return code + " | " + name + " | " + status;
    }
}