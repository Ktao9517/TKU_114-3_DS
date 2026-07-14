import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[][] sales = new int[3][4];
        
        inputSales(sc, sales);
        
        displayTable(sales);
        
        int[] productTotals = calculateProductTotals(sales);
        int[] dayTotals = calculateDayTotals(sales);
        
        displayProductTotals(productTotals);
        displayDayTotals(dayTotals);
        
        int bestIdx = findBestProduct(productTotals);
        System.out.println("\n總銷售量最高的商品是第 " + (bestIdx + 1) + " 項商品，銷售量: " + productTotals[bestIdx]);
        
        sc.close();
    }
    
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                while (true) {
                    System.out.print("請輸入商品 " + (i + 1) + " 第 " + (j + 1) + " 天銷售量 (數值不得小於 0): ");
                    if (sc.hasNextInt()) {
                        int val = sc.nextInt();
                        if (val >= 0) {
                            sales[i][j] = val;
                            break;
                        }
                    } else {
                        sc.next();
                    }
                    System.out.println("銷售量必須大於0，請重新輸入。");
                }
            }
        }
    }
    
    public static void displayTable(int[][] sales) {
        System.out.println("\n========== 銷售量表格 ==========");
        System.out.print("商品\\天\t");
        for (int d = 1; d <= 4; d++) {
            System.out.print("第" + d + "天\t");
        }
        System.out.println("商品總計");
        
        for (int i = 0; i < 3; i++) {
            System.out.print("商品" + (i + 1) + "\t");
            int rowSum = 0;
            for (int j = 0; j < 4; j++) {
                System.out.print(sales[i][j] + "\t");
                rowSum += sales[i][j];
            }
            System.out.println(rowSum);
        }
        
        System.out.print("每日總計\t");
        for (int j = 0; j < 4; j++) {
            int colSum = 0;
            for (int i = 0; i < 3; i++) {
                colSum += sales[i][j];
            }
            System.out.print(colSum + "\t");
        }
        System.out.println();
        System.out.println("==================================");
    }
    
    public static int[] calculateProductTotals(int[][] sales) {
        int[] totals = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                totals[i] += sales[i][j];
            }
        }
        return totals;
    }
    
    public static int[] calculateDayTotals(int[][] sales) {
        int[] totals = new int[4];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                totals[j] += sales[i][j];
            }
        }
        return totals;
    }
    
    public static void displayProductTotals(int[] totals) {
        System.out.println("\n各商品銷售總量:");
        for (int i = 0; i < totals.length; i++) {
            System.out.println("商品 " + (i + 1) + ": " + totals[i] + " 件");
        }
    }
    
    public static void displayDayTotals(int[] totals) {
        System.out.println("\n每日銷售總量:");
        for (int i = 0; i < totals.length; i++) {
            System.out.println("第 " + (i + 1) + " 天: " + totals[i] + " 件");
        }
    }
    
    public static int findBestProduct(int[] productTotals) {
        int maxIdx = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}