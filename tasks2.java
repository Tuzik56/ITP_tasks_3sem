import java.util.Arrays;
import java.util.Random;

public class tasks2 {
    public static void main(String[] args) {
        System.out.println(task1("orange"));
        System.out.println(task2("Ryan Gosling"));
        System.out.println(task3(new int[]{44, 32, 86, 19}));
        System.out.println(task4(new float[]{1, 2, 3, 4, 5}));
        System.out.println(task5(new int[]{3, 3, -2, 408, 3, 31}));
        System.out.println(task6("Hello World"));
        System.out.println(task7(11));
        System.out.println(task8(5));
        System.out.println(task9("Help me"));
        System.out.println(task10("listen", "silent"));
    }

    public static boolean task1 (String line) {
        boolean temp = true;
        int i = 0;
        while (temp & i < line.length()) {
            int j = i + 1;
            while (temp & j < line.length()) {
                if (line.charAt(i) == line.charAt(j)) {
                    temp = false;
                }
                j++;
            }
            i++;
        }
        return !temp;
    }

    public static String task2 (String line) {
        String[] temp = line.split("\\s");
        return (temp[0].charAt(0) + "" + temp[1].charAt(0));
    }

    public static int task3 (int[] list) {
        int even = 0;
        int odd = 0;
        for (int i: list) {
            if (i % 2 == 0) {
                even += i;
            }
            else {
                odd += i;
            }
        }
        return Math.abs(even - odd);
    }

    public static boolean task4 (float[] list) {
        float sum = 0;
        int i = 0;
        boolean temp = true;
        for (float number: list) {
            sum += number;
        }
        float mean = sum / list.length;
        while (i < list.length & temp) {
            if (list[i] == mean) {
                temp = false;
                return true;
            }
            i++;
        }
        return false;
    }

    public static String task5 (int[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] *= i;
        }
        return Arrays.toString(list);
    }

    public static String task6 (String line) {
        String reverseLine = "";
        for (char ch: line.toCharArray()) {
            reverseLine = ch + reverseLine;
        }
        return reverseLine;
    }

    public static int task7 (int g) {
        int n1 = 0;
        int n2 = 0;
        int n3 = 1;
        for (int i = 0; i < g-3; i++) {
            int temp = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        return n3;
    }

    public static String task8 (int len) {
        Random rand = new Random();
        String line = "";
        for (int i = 0; i < len; i++) {
            line += Integer.toHexString(rand.nextInt(16));
        }
        return line;
    }

    public static String task9 (String line) {
        line = line.toLowerCase();
        boolean answer = true;
        int i = 0;
        while (i + 4 <= line.length() & answer) {
            if (line.substring(i, i + 4).equals("help")) {
                answer = false;
                return "Calling for a staff member";
            }
            i++;
        }
        return "Keep waiting";
    }

    public static boolean task10 (String line1, String line2) {
        char[] list1 = line1.toCharArray();
        char[] list2 = line2.toCharArray();
        Arrays.sort(list1);
        Arrays.sort(list2);
        return Arrays.equals(list1, list2);
    }
}
