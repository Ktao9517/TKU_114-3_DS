public class RangeSearchSystem {
    public static void main(String[] args) {
        
        int[] data = {5, 10, 10, 10, 18, 25, 25, 25, 25, 40, 50};

        System.out.println("===== 第一筆與最後一筆位置 =====");
        System.out.print("陣列: ");
        for (int n : data) System.out.print(n + " ");
        System.out.println("\n");

        printRange(data, 10);
        printRange(data, 25);
        printRange(data, 5);   
        printRange(data, 50);  
        printRange(data, 99);  
    }

    public static void printRange(int[] data, int target) {
        int first = findFirst(data, target);
        int last = findLast(data, target);
        if (first == -1) {
            System.out.println("目標 " + target + " → [-1, -1]，出現次數 0");
        } else {
            System.out.println("目標 " + target + " → [" + first + ", " + last + "]，出現次數 " + (last - first + 1));
        }
    }

    public static int findFirst(int[] data, int target) {
        int left = 0, right = data.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static int findLast(int[] data, int target) {
        int left = 0, right = data.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
