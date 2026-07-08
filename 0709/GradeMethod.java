public class GradeMethod { 
    public static void main(String[] args) { 
        int javaScore = 100; 
        int englishScore = 93; 
        int mathScore = 100; 
        double average = calculateAverage(javaScore, englishScore, mathScore); 
        String level = getGrade(average); 
        System.out.println("Average: " + average);
        System.out.println("Level: " + level); 
    } 

    public static double calculateAverage(int javaScore, int englishScore, int mathScore) { 
        return (javaScore + englishScore + mathScore) / 3.0; 
    } 

    public static String getGrade(double average) { 
        if (average >= 90) { 
            return "A"; 
        } else if (average >= 80) { 
            return "B"; 
        } else if (average >= 70) { 
            return "C"; 
        } else if (average >= 60) { 
            return "D"; 
        } else { 
            return "F"; 
        } 
    } 
}

