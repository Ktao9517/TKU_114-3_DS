public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5.0, 3.0);
        Rectangle r2 = new Rectangle(4.0, 4.0);
        Rectangle r3 = new Rectangle(6.5, 2.5);
        Rectangle r4 = new Rectangle(10.0, 10.0); // 額外一個

        System.out.println("========== 矩形示範 ==========");
        
        displayRectangleInfo(1, r1);
        displayRectangleInfo(2, r2);
        displayRectangleInfo(3, r3);
        displayRectangleInfo(4, r4);

        System.out.println("\n--- 測試 setter 驗證 ---");
        System.out.println("嘗試將 r1 寬度設為 -1: " + r1.setWidth(-1));
        System.out.println("r1 目前寬度仍為: " + r1.getWidth());
        System.out.println("嘗試將 r1 高度設為 0: " + r1.setHeight(0));
        System.out.println("r1 目前高度仍為: " + r1.getHeight());
        System.out.println("成功將 r1 寬度設為 7.5: " + r1.setWidth(7.5));
        System.out.println("更新後 r1: " + r1);
    }

    private static void displayRectangleInfo(int no, Rectangle r) {
        System.out.println("\n矩形 " + no + ":");
        System.out.println("  寬: " + r.getWidth() + ", 高: " + r.getHeight());
        System.out.println("  面積: " + r.calculateArea());
        System.out.println("  周長: " + r.calculatePerimeter());
        System.out.println("  是否為正方形: " + (r.isSquare() ? "是" : "否"));
        System.out.println("  toString(): " + r);
    }
}