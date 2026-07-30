public class RepairTask {
    private String id;
    private String equipment;
    private int priority;
    private boolean completed;

    public RepairTask(String id, String equipment, int priority) {
        this.id = id.trim();
        this.equipment = equipment.trim();
        this.priority = priority;
        this.completed = false;
    }

    public String getId() { return id; }
    public String getEquipment() { return equipment; }
    public int getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        return id + " | " + equipment + " | 優先:" + priority + (completed ? " [完成]" : " [等待]");
    }
}
