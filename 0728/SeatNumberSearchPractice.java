import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        // 至少 12 筆已排序座位編號
        int[] seats = {101, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160};

        System.out.println("===== 座位編號二分搜尋 =====");
        System.out.print("座位編號: ");
        for (int s : seats) System.out.print(s + " ");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號: ");
        int target = sc.nextInt();

        int index = binarySearch(seats, target);
        if (index != -1) {
            System.out.println("找到！索引 = " + index + "，座位 = " + seats[index]);
        } else {
            System.out.println("找不到座位編號 " + target);
        }
        sc.close();
    }

    public static int binarySearch(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;
        int round = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("第 " + round + " 輪 → low=" + low + ", mid=" + mid + ", high=" + high +
                               " (值=" + data[mid] + ")");

            if (data[mid] == target) {
                return mid;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            round++;
        }
        return -1;
    }
}
