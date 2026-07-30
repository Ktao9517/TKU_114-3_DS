public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] list = {
            new Contestant("C01", "Amy", 95, 120),
            new Contestant("C02", "Ben", 88, 100),
            new Contestant("C03", "Cara", 95, 110),
            new Contestant("C04", "Dan", 70, 90),
            new Contestant("C05", "Eve", 88, 95)
        };

        System.out.println("===== 參賽者排名系統 =====");
        insertionSort(list);

        int rank = 1;
        for (int i = 0; i < list.length; i++) {
            if (i > 0 && (list[i].getScore() != list[i-1].getScore() ||
                          list[i].getFinishSeconds() != list[i-1].getFinishSeconds())) {
                rank = i + 1;
            }
            System.out.println("第 " + rank + " 名: " + list[i]);
        }
    }

    public static void insertionSort(Contestant[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Contestant key = arr[i];
            int pos = i - 1;
            while (pos >= 0 && compare(arr[pos], key) > 0) {
                arr[pos + 1] = arr[pos];
                pos--;
            }
            arr[pos + 1] = key;
        }
    }

    private static int compare(Contestant a, Contestant b) {
        if (a.getScore() != b.getScore()) {
            return b.getScore() - a.getScore(); // 高分在前
        }
        return a.getFinishSeconds() - b.getFinishSeconds(); // 秒數少在前
    }
}
