public class DeliveryTask {
    private String id;
    private String address;
    private String item;

    public DeliveryTask(String id, String address, String item) {
        this.id = id.trim();
        this.address = address.trim();
        this.item = item.trim();
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return id + " | " + item + " → " + address;
    }
}