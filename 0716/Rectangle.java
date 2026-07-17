public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean setWidth(double width) {
        if (width <= 0) {
            return false;
        }
        this.width = width;
        return true;
    }

    public boolean setHeight(double height) {
        if (height <= 0) {
            return false;
        }
        this.height = height;
        return true;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return Math.abs(width - height) < 1e-9;
    }

    @Override
    public String toString() {
        return String.format("Rectangle[width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f, isSquare=%s]",
                width, height, calculateArea(), calculatePerimeter(), isSquare());
    }
}