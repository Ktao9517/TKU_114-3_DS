import java.util.Scanner;
public class HealthAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String again = "y";

        while (again.equalsIgnoreCase("y")) {

            System.out.print("請輸入姓名：");
            String name = sc.nextLine();

            System.out.print("請輸入身高（公尺）：");
            double height = sc.nextDouble();

            System.out.print("請輸入體重（公斤）：");
            double weight = sc.nextDouble();

            double bmi = weight / (height * height);

            System.out.println("\n=== BMI Report ===");
            System.out.println("Name: " + name);
            System.out.println("BMI: " + bmi);

            if (bmi < 18.5) {
                System.out.println("Level: Underweight");
            } else if (bmi < 24) {
                System.out.println("Level: Normal");
            } else if (bmi < 27) {
                System.out.println("Level: Overweight");
            } else {
                System.out.println("Level: Obese");
            }

            System.out.print("\n是否繼續輸入下一筆？(y/n)：");
            sc.nextLine(); 
            again = sc.nextLine();
        }

        sc.close();
    }
}
