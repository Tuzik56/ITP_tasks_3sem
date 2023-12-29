public class tasks1 {
    public static void main(String[] args) {
        System.out.println(task1(5));
        System.out.println(task2(15, 1));
        System.out.println(task3(3, 4, 2));
        System.out.println(task4(5, 5, 5));
        System.out.println(task5(8, 4));
        System.out.println(task6(22, 1.4f, 2.0f));
        System.out.println(task7(3));
        System.out.println(task8(48, 14));
        System.out.println(task9(70, 1500));
        System.out.println(task10(123, 58));
    }

    public static float task1 (int i) {
        return i * 3.785f;
    }

    public static int task2 (int time, int intensity) {
        return time * intensity;
    }

    public static int task3 (int a, int b, int c) {
        return a * 20 + b * 50 + c * 100;
    }

    public static String task4 (int a, int b, int c) {
        String answer = "not a triangle";
        if (a + b > c & a + c > b & b + c > a) {
            if (a == b & b == c){
                answer = "isosceles";
            }
            else if (a == b | a == c | b == c ) {
                answer = "equilateral";
            }
            else {
                answer = "different-sided";
            }
        }
        return answer;
    }

    public static int task5 (int a, int b) {
        return a > b ? a : b;
    }

    public static int task6 (float n, float w, float h) {
        return (int) (n / (2 * w * h));
    }

    public static int task7 (int number) {
        if (number >= 0) {
            int factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial = factorial * i;
            }
            return factorial;
        }
        else {
            return 0;
        }
    }

    public static int task8 (int a, int b) {
        while (a != b) {
            if (a > b) { a -= b; }
            else { b -= a; }
        }
        return a;
    }

    public static float task9 (int amount, int cost) {
        return amount * cost * 0.72f;
    }

    public static int task10 (float student, int table) {
        if (student / 2 <= table) {
            return 0;
        }
        else {
            return (int) Math.ceil(student / 2 - table);
        }
    }
}
