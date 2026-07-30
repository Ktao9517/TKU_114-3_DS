public class Registration {
    private String id;         
    private String name;        
    private String phone;
    private boolean isWaitlist; 
    private boolean cancelled;

    public Registration(String id, String name, String phone) {
        this.id = id.trim();
        this.name = name.trim();
        this.phone = phone == null ? "" : phone.trim();
        this.isWaitlist = false;
        this.cancelled = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public boolean isWaitlist() { return isWaitlist; }
    public void setWaitlist(boolean waitlist) { this.isWaitlist = waitlist; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    @Override
    public String toString() {
        String status = cancelled ? "[已取消]" : (isWaitlist ? "[候補]" : "[正式]");
        return id + " | " + name + " | " + phone + " " + status;
    }
}
