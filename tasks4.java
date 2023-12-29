import java.util.Arrays;
import java.util.Hashtable;

public class tasks4 {
    public static void main(String[] args) {
        System.out.println(task1("abracadabra"));
        System.out.println(Arrays.toString(task2(5)));
        System.out.println(Arrays.toString(task3(3)));
        System.out.println(task4("abcdjuwx"));
        System.out.println(task5("aaabbcdd"));
        System.out.println(task6("tisyachya sixty seven"));
        System.out.println(task7("77897898"));
        System.out.println(task8(3, new int[][]{
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}}));
        System.out.println(task9("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(task10(519, 723));
    }

    public static String task1 (String line) {
        if (line.length() <= 1) {
            return line;
        }
        else {
            for (int i = 1; i < line.length(); i++) {
                if (line.charAt(0) == line.charAt(i)) {
                    line = line.substring(0, i) + line.substring(i+1);
                }
            }
            return line.charAt(0) + task1(line.substring(1));
        }
    }

    public static String[] task2 (int n) {
        String num = "";
        String[] output = {};

        for (int i = (int) Math.pow(2, n*2-1); i < (int) Math.pow(2, n*2); i++) {
            num = Integer.toBinaryString(i);
            int j = 0;

            while (j < num.length()-1) {
                if (num.startsWith("10", j)) {
                    num = num.substring(0, j) + num.substring(j+2);
                    j = 0;
                }
                else { j++; }
            }

            if (num.isEmpty()) {
                num = Integer.toBinaryString(i);
                num = num.replace("1", "(");
                num = num.replace("0", ")");
                output = Arrays.copyOf(output, output.length+1);
                output[output.length-1] = num;
            }
        }
        return output;
    }

    public static String[] task3 (int n) {
        String[] output = {};
        for (int i = (int) Math.pow(2, n); i < Math.pow(2, n+1); i++) {
            String line = Integer.toBinaryString(i);
            line = line.substring(1);
            if (!line.contains("00")) {
                output = Arrays.copyOf(output, output.length+1);
                output[output.length-1] = line;
            }
        }
        return output;
    }

    public static String task4 (String line) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String maxLine = "";
        line = line.toLowerCase();
        while (line.length() > 1) {
            int i = 0;
            String tempLine = String.valueOf(line.charAt(i));
            if (Math.abs(alphabet.indexOf(line.charAt(i)) - alphabet.indexOf(line.charAt(i+1))) == 1) {
                int order = alphabet.indexOf(line.charAt(i)) - alphabet.indexOf(line.charAt(i + 1));
                while (i < line.length() - 1 && alphabet.indexOf(line.charAt(i)) - alphabet.indexOf(line.charAt(i + 1)) == order) {
                    tempLine += line.charAt(i + 1);
                    i++;
                }
                if (tempLine.length() > maxLine.length()) {
                    maxLine = tempLine;
                }
            }
            line = line.substring(i+1);
        }
        return maxLine;
    }

    public static String task5 (String line) {
        String str = "";
        while (line.length() > 1) {
            int count = 1;
            int j = 0;
            while (j < line.length()-1 && line.charAt(j) == line.charAt(j+1)) {
                count++;
                j++;
            }
            str = str + (line.charAt(j) + Integer.toString(count));
            line = line.substring(j+1);
        }
        int i = 1;
        while (i<str.length()-2) {
            if (str.charAt(i) > str.charAt(i+2)) {
                str = str.substring(0, i-1) + str.substring(i+1) + str.substring(i-1, i+1);
                i = 1;
            }
            else {
                i += 2;
            }
        }
        return str;
    }

    public static int task6 (String line) {
        String[] massive = line.split(" ");
        int number = 0;

        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);
        table.put("four", 4);
        table.put("five", 5);
        table.put("six", 6);
        table.put("seven", 7);
        table.put("eight", 8);
        table.put("nine", 9);
        table.put("ten", 10);
        table.put("eleven", 11);
        table.put("twelve", 12);
        table.put("thirteen", 13);
        table.put("fourteen", 14);
        table.put("fifteen", 15);
        table.put("sixteen", 16);
        table.put("seventeen", 17);
        table.put("eighteen", 18);
        table.put("nineteen", 19);
        table.put("twenty", 20);
        table.put("thirty", 30);
        table.put("forty", 40);
        table.put("fifty", 50);
        table.put("sixty", 60);
        table.put("seventy", 70);
        table.put("eighty", 80);
        table.put("ninety", 90);
        table.put("tisyachya", 1000);

        for (String word: massive) {
            if (!word.equals("hundred")){
                number += table.get(word.toLowerCase());
            }
            else {
                number *= 100;
            }
        }

        return number;
    }

    public static String task7 (String line) {
        String maxLine = "";
        int i = 0;
        while (i < line.length()) {
            String tempLine = "";
            int j = i;
            while (j < line.length()) {
                if (!tempLine.contains(Character.toString(line.charAt(j)))) {
                    tempLine += line.charAt(j);
                }
                j++;
            }
            i++;
            if (tempLine.length() > maxLine.length()) {
                maxLine = tempLine;
            }
        }
        return maxLine;
    }

    public static int task8 (int n, int[][] array1) {
        int[][] array2 = new int[n][n];
        array2[0][0] = array1[0][0];

        int i = 1;
        while (i < n) {
            array2[0][i] = array1[0][i] + array2[0][i-1];
            array2[i][0] = array1[i][0] + array2[i-1][0];
            i++;
        }

        i = 1;
        while (i < n) {
            int j = 1;
            while (j < n) {
                array2[i][j] = Integer.min(array2[i-1][j], array2[i][j-1]) + array1[i][j];
                j++;
            }
            i++;
        }

        return array2[n-1][n-1];
    }

    public static String task9 (String line) {
        String[] array1 = line.split(" ");
        String[] array2 = new String[array1.length];
        StringBuilder output = new StringBuilder();
        for (String word: array1) {
            for (int i=0; i<word.length(); i++) {
                try {
                    int n = Integer.parseInt(String.valueOf(word.charAt(i)));
                    array2[n-1] = word.replace(String.valueOf(word.charAt(i)), "");
                } catch (NumberFormatException e) {
                }
            }
        }
        for (String word: array2) {
            output.append(word).append(" ");
        }

        return output.toString();
    }

    public static Integer task10 (int number1, int number2) {
        char[] array1 = Integer.toString(number1).toCharArray();
        Arrays.sort(array1);
        char[] array2 = Integer.toString(number2).toCharArray();
        int i = 0;
        while (i < array2.length){
            int j = 0;
            while (j < array1.length) {
                if (array2[i] < array1[j]) {
                    array2[i] = array1[j];
                    array1[j] = 0;
                }
                j++;
            }
            i++;
        }
        return Integer.valueOf(String.valueOf(array2));
    }
}
