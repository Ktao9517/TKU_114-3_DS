public class Product {
    private String name;
    private int price;
    private int stock;

    public Product(String name, int price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim();
        }
        this.price = Math.max(price, 1);   // 價格至少為 1
        this.stock = Math.max(stock, 0);   // 庫存不得小於 0
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean setPrice(int price) {
        if (price <= 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    public boolean restock(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        stock += quantity;
        return true;
    }

    public boolean sell(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            return false;
        }
        stock -= quantity;
        return true;
    }

    public boolean isLowStock() {
        return stock < 10;
    }

    public long getInventoryValue() {
        return (long) price * stock;
    }

    @Override
    public String toString() {
        return String.format("%s，價格：%d，庫存：%d", name, price, stock);
    }
}