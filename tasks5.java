import java.util.*;

public class tasks5 {
    public static void main(String[] args) {
        System.out.println("__________task_1__________");
        System.out.println(task1("ABAB", "CDCD"));
        System.out.println(task1("ABCBA", "BCDCB"));
        System.out.println(task1("FFGG", "CDCD"));
        System.out.println(task1("FFFF", "ABCD"));

        System.out.println("__________task_2__________");
        System.out.println(Arrays.deepToString(task2("H3", "E2")));
        System.out.println(Arrays.deepToString(task2("A4", "B2")));
        System.out.println(Arrays.deepToString(task2("A4", "C2")));
        System.out.println(Arrays.deepToString(task2("G3", "F4")));

        System.out.println("__________task_3__________");
        System.out.println(task3(0));
        System.out.println(task3(10));

        System.out.println("__________task_4__________");
        System.out.println(task4(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(task4(new String[]{"trance", "recant"}, "recant"));
        System.out.println(task4(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));

        System.out.println("__________task_5__________");
        System.out.println(Arrays.deepToString(task5(new int[]{1, 6, 5, 4, 8, 2, 3, 7})));

        System.out.println("__________task_6__________");
        System.out.println(task6(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(task6(new String[]{"10%"}));
        System.out.println(task6(new String[]{"53%", "79%"}));

        System.out.println("__________task_7__________");
        System.out.println(task7("encode", "hello world", 3));
        System.out.println(task7("decode", "EPQSWX PEWX XEWO!", 4));

        System.out.println("__________task_8__________");
        System.out.println((int) task8(5, 3));

        System.out.println("__________task_9__________");
        System.out.println(task9("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(task9("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(task9("New York", "December 31, 1970 13:40", "Beijing"));


        System.out.println("__________task_10_________");
        System.out.println(task10(3));
        System.out.println(task10(30));
        System.out.println(task10(321));
        System.out.println(task10(123));
    }

    public static boolean task1(String line1, String line2) {
        String tempLine = line1;
        if (line1.length() == line2.length()) {
            for (int i = 0; i < line1.length(); i++) {
                if (line1.charAt(i) != ' ') {
                    line2 = line2.replace(line2.charAt(i), line1.charAt(i));
                    line1 = line1.replace(line1.charAt(i), ' ');
                }
            }
            return tempLine.equals(line2);
        }
        return false;
    }

    public static String[] task2(String spiderPoint, String flyPoint) {
        String points = "ABCDEFGH";
        String rvPoints = "HGFEDCBA";
        float pathLength = 1;                                             //расстояние между кольцами

        //координаты павука
        int[] spider = new int[]{1, Integer.parseInt(String.valueOf(spiderPoint.charAt(1)))}; //букву паука принимаем за 1

        int flyRadial = points.indexOf(spiderPoint.charAt(0)) - points.indexOf(flyPoint.charAt(0));
        //по часовой или против
        if (flyRadial >= 0) {
            points = rvPoints;
        }
        //координаты мухи
        int[] fly = new int[]{Math.abs(flyRadial) + 1, Integer.parseInt(String.valueOf(flyPoint.charAt(1)))};

        int[][] path = new int[][]{Arrays.copyOf(spider, spider.length)}; //конечный маршрут павука до мухи
        float tempPathCenterLength = 0;                                   //длина маршрута через центр
        int[][] tempPathCenter = new int[][]{};                           //маршрут через центр
        float tempPathCircleLength = 0;                                   //длина маршрута через кольцо
        int[][] tempPathCircle = new int[][]{};                           //маршрут через кольцо

        if (spider[1] > fly[1]) {                                         //если муха ближе к центру
            path = moveOnLine(spider, fly, path);

            //первый вариант: идем через центр
            //идем к центру
            int[] tempSpiderCenter = Arrays.copyOf(spider, spider.length);
            tempPathCenter = moveOnLine(tempSpiderCenter, new int[]{0, 0}, tempPathCenter);
            tempPathCenterLength += linePathLength(tempPathCenter, pathLength);
            //меняем ветку и идем от центра
            tempSpiderCenter[0] = fly[0];
            tempPathCenter = moveOnLine(tempSpiderCenter, fly, tempPathCenter);
            tempPathCenterLength += linePathLength(tempPathCenter, pathLength);

            //второй вариант: идем по кольцу
            int[] tempSpiderCircle = Arrays.copyOf(spider, spider.length);
            tempPathCircle = moveOnCircle(tempSpiderCircle, fly, tempPathCircle);
            tempPathCircleLength += circlePathLength(tempPathCircle, tempSpiderCircle[1], pathLength);

            //смотрим че короче
            if (tempPathCenterLength < tempPathCircleLength) {
                path = append(path, tempPathCenter);
            } else {
                path = append(path, tempPathCircle);
            }

            return output(path, points, 1);

        } else {                                                          //если паук ближе к центру
            path = moveOnCircle(spider, fly, path);
            path = moveOnLine(spider, fly, path);

            return output(path, points, 0);
        }
    }

    public static int[][] append(int[][] ar1, int[][] ar2) { //присоединяем к первому массиву второй
        int index = ar1.length;
        ar1 = Arrays.copyOf(ar1, ar1.length + ar2.length);
        for (int[] p : ar2) {
            ar1[index] = p;
            index++;
        }
        return ar1;
    }

    public static String[] output(int[][] path, String points, int index) { //меняем цифры на буквы
        String[] ar = new String[path.length];
        for (int i=0; i<path.length; i++) {
            String point = String.valueOf(points.charAt(path[i][0] - index));
            ar[i] = point + path[i][1];
        }
        return ar;
    }

    public static float linePathLength(int[][] path, float pathLength) { //считаем длину пройденной линии
        return Math.abs(path[0][1] - path[path.length-1][1]) * pathLength;
    }

    public static float circlePathLength(int[][] path, int point, float pathLength) { //считаем длину пройденного кольца
        return (float) (Math.abs(path[0][0] - path[path.length-1][0]) * Math.sqrt(2 - Math.sqrt(2)) * point * pathLength);
    }

    public static int[][] moveOnLine(int[] spider, int[] fly, int[][] path) { //двигаем павучка по линии
        while (spider[1] != fly[1]) {
            if (spider[1] > fly[1]) {
                spider[1] = spider[1] - 1;
            } else {
                spider[1] = spider[1] + 1;
            }
            path = Arrays.copyOf(path, path.length + 1);
            path[path.length - 1] = Arrays.copyOf(spider, spider.length);
        }
        return path;
    }

    public static int[][] moveOnCircle(int[] spider, int[] fly, int[][] path) { //двигаем павучка по кругу
        while (spider[0] != fly[0]) {
            if (spider[0] > fly[0]) {
                spider[0] = spider[0] - 1;
            } else {
                spider[0] = spider[0] + 1;
            }
            path = Arrays.copyOf(path, path.length + 1);
            path[path.length - 1] = Arrays.copyOf(spider, spider.length);
        }
        return path;
    }

    public static int task3(int n) {
        if (n < 10) {
            return 1;
        }
        return 1 + task3(n/10);
    }

    public static int task4(String[] words, String word) {
        int[] score = new int[4];
        int result = 0;
        HashMap<String, Integer> letters = new HashMap<>();
        while (!word.isEmpty()) {
            String letter = String.valueOf(word.charAt(0));
            int count = word.length() - word.replace(letter, "").length();
            word = word.replace(letter, "");
            letters.put(letter, count);
        }

        for (String w: words) {
            int length = w.length();

            while (!w.isEmpty()) {
                String letter = String.valueOf(w.charAt(0));
                int count = w.length() - w.replace(letter, "").length();
                w = w.replace(letter, "");

                if (count > letters.get(letter)) {
                    length = 0;
                    break;
                }
            }
            if (length != 0) {
                score[length-3]++;
            }
        }

        for (int i=0; i<3; i++) {
            result += score[i] * (i + 1);
        }
        if (score[3] > 1) {
            result += score[3] * 54;
        } else {
            result += score[3] * 4;
        }

        return result;
    }

    public static int[][] task5(int[] array) {
        int[][] output = {};
        Arrays.sort(array);
        int i = 0;
        while (i < array.length) {
            int j = array.length-1;
            while (j > i) {
                if (array[i]+array[j] == 8) {
                    output = Arrays.copyOf(output, output.length+1);
                    output[output.length-1] = new int[]{array[i], array[j]};
                    break;
                }
                j--;
            }
            i++;
        }
        return output;
    }

    public static String task6(String[] array) {
        float sum = 0;
        for (String n : array) {
            sum += Integer.parseInt(n.substring(0, n.length() - 1));
        }
        float average = sum/array.length;
        float mark = (average-5) * (array.length+1) - sum;

        return Math.round(mark) + "%";
    }

    public static String task7(String type, String text, int step) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        text = text.toUpperCase();
        StringBuilder output = new StringBuilder();

        if (Objects.equals(type, "decode")) {
            step *= -1;
        }

        for (int i=0; i<text.length(); i++) {
            char symbol = text.charAt(i);
            int index = alphabet.indexOf(symbol);
            if (index != -1) {
                int newIndex = index + step;
                while (!(0 <= newIndex && newIndex < alphabet.length())) {
                    newIndex -= alphabet.length();
                }
                output.append(alphabet.charAt(newIndex));
            }
            else {
                output.append(symbol);
            }
        }
        return output.toString();
    }

    public static float task8(float n, float k) {
        float tempN = n;
        if (n > 1) {
            tempN -= 1;
            if (n - k == 0) {
                k -= 1;
            }
            return (n/(n-k)) * task8(tempN, k);
        }
        if (n - k == 0) {
            k -= 1;
        }
        return (n/(n-k));
    }


    public static String task9(String city1, String date, String city2) {
        HashMap<String, Integer> cityTime = new HashMap<>();
        cityTime.put("Los Angeles", -8 * 60);
        cityTime.put("New York", -5 * 60);
        cityTime.put("Caracas", (int) (-5.5f * 60));
        cityTime.put("Buenos Aires", -3 * 60);
        cityTime.put("London", 0);
        cityTime.put("Rome", 60);
        cityTime.put("Moscow", 3 * 60);
        cityTime.put("Tehran", (int) (3.5f * 60));
        cityTime.put("New Delhi", (int) (5.5f * 60));
        cityTime.put("Beijing", 8 * 60);
        cityTime.put("Canberra", 10 * 60);

        HashMap<String, Integer> months = new HashMap<>();
        months.put("January", 0);
        months.put("February", 31);
        months.put("March", 59);
        months.put("April", 90);
        months.put("May", 120);
        months.put("June", 151);
        months.put("July", 181);
        months.put("August", 212);
        months.put("September", 242);
        months.put("October", 273);
        months.put("November", 303);
        months.put("December", 334);

        HashMap<Integer, Integer> monthsN = new HashMap<>();
        monthsN.put(1, 0);
        monthsN.put(2, 31);
        monthsN.put(3, 59);
        monthsN.put(4, 90);
        monthsN.put(5, 120);
        monthsN.put(6, 151);
        monthsN.put(7, 81);
        monthsN.put(8, 212);
        monthsN.put(9, 242);
        monthsN.put(10, 273);
        monthsN.put(11, 303);
        monthsN.put(12, 334);


        int inputYear = Integer.parseInt(date.split(", ")[1].split(" ")[0]);
        int inputMonth = months.get(date.split(", ")[0].split(" ")[0]);
        int inputDay = Integer.parseInt(date.split(", ")[0].split(" ")[1]);
        int inputMinute = Integer.parseInt(date.split(", ")[1].split(" ")[1].split(":")[0]) * 60 +
                Integer.parseInt(date.split(", ")[1].split(" ")[1].split(":")[1]);

        int inputTime = inputYear * 526000 + (inputMonth + inputDay) * 1440 + inputMinute;
        int outputTime = inputTime + (cityTime.get(city2) - cityTime.get(city1));

        int outputYear = (outputTime / 526000);
        outputTime -= outputYear * 526000;
        int outputMonth = outputTime/ 43800 + 1;

        int outputDay = outputTime / 1440 - monthsN.get(outputMonth);

        outputTime -= (outputDay + monthsN.get(outputMonth)) * 1440;
        if (outputDay == 0) {outputDay += 1;}
        int outputHour = outputTime / 60;
        int outputMinute = outputTime - outputHour * 60;

        return outputYear + "/" + outputMonth + "/" + outputDay + " " + timeFormat(outputHour) + ":" + timeFormat(outputMinute);
    }

    private static String timeFormat(int num) {
        String time = String.valueOf(num);
        String out = "0";
        if (time.length() == 1) {
            return out + time;
        } else {
            return time;
        }
    }


    public static boolean task10(int n1) {
        char[] digits = Integer.toString(n1).toCharArray();
        Arrays.sort(digits);
        int i = 1;

        while (digits[0] == '0') {
            digits[0] = digits[i];
            digits[i] = '0';
            i++;
        }
        int n2 = Integer.parseInt(new String(digits));

        return n1 == n2;
    }
}
