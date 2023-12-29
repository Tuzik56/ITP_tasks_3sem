import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tasks6 {
    public static void main(String[] args) {
        System.out.println("__________task_1__________");
        System.out.println(task1("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(task1("An old west action hero actor", "Clint Eastwood"));
        System.out.println(task1("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(task1("Banana? margaritas", "ANAGRAM"));
        System.out.println(task1("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(task1("Bright is the moon", "Bongo mirth"));


        System.out.println("__________task_2__________");
        System.out.println(task2("intercontinentalisationalism", 6));
        System.out.println(task2("strengths", 3));
        System.out.println(task2("pneumonoultramicroscopicsilicovolcanoconiosis", 15));

        System.out.println("__________task_3__________");
        System.out.println(task3("myworldevolvesinhers", "tesh"));
        System.out.println(task3("andiloveherso", "tesha"));
        System.out.println(task3("mubashirhassan", "crazy"));
        System.out.println(task3("edabitisamazing", "matt"));
        System.out.println(task3("iloveher", "612345"));

        System.out.println("__________task_4__________");
        System.out.println(Arrays.toString(task4(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(task4(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(task4(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(task4(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(task4(new int[]{100, 12, 4, 1, 2}, 15)));

        System.out.println("__________task_5__________");
        System.out.println(Arrays.toString(task5(6)));
        System.out.println(Arrays.toString(task5(24)));
        System.out.println(Arrays.toString(task5(125)));
        System.out.println(Arrays.toString(task5(720)));
        System.out.println(Arrays.toString(task5(1024)));
        System.out.println(Arrays.toString(task5(40320)));

        System.out.println("__________task_6__________");
        System.out.println(task6("0.(6)"));
        System.out.println(task6("1.(1)"));
        System.out.println(task6("3.(142857)"));
        System.out.println(task6("0.19(2367)"));
        System.out.println(task6("0.1097(3)"));

        System.out.println("__________task_7__________");
        System.out.println(task7("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println(task7("FORALOOP"));
        System.out.println(task7("CANIMAKEAGUESSNOW"));
        System.out.println(task7("33314444"));
        System.out.println(task7("TOP"));
        System.out.println(task7("X"));
        System.out.println(task7(""));

        System.out.println("__________task_8__________");
        System.out.println(task8("3 + 5 * (2 - 6)"));
        System.out.println(task8("6 - 18 / (-1 + 4)"));

        System.out.println("__________task_9__________");
        System.out.println(task9("aabbcd"));
        System.out.println(task9("aabbccddeefghi"));
        System.out.println(task9("abcdefghhgfedecba"));

        System.out.println("__________task_10_________");
        System.out.println(task10("abcd", "bd"));
        System.out.println(task10("aggtab", "gxtxamb"));
        System.out.println(task10("sdnvlsnvs", "sdnvfdkjvvlsnvdkn"));
    }

    public static String task1(String sentence, String anagram) {
        String cleanedSentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedAnagram = anagram.replaceAll("[^a-zA-Z]", "").toLowerCase();
        char[] anagramArray = cleanedAnagram.toCharArray();

        for (int i = 0; i <= cleanedSentence.length() - cleanedAnagram.length(); i++) {
            String substring = cleanedSentence.substring(i, i + cleanedAnagram.length());

            char[] substringArray = substring.toCharArray();

            Arrays.sort(anagramArray);
            Arrays.sort(substringArray);

            if (Arrays.equals(anagramArray, substringArray)) {
                return substring;
            }
        }

        return "";
    }

    public static List<String> task2(String line, int length) {
        List<String> list = new ArrayList<>();
        list = getSubstrings(line, length, list);

        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    public static List<String> getSubstrings(String line, int length, List<String> list) {
        if (line.length() >= length) {
            if (line.length() < length * 2) {
                list.add(line.substring(0, length));
            } else {
                list.add(line.substring(0, length));
                list = getSubstrings(line.substring(length), length, list);
            }
            return list;
        }
        return null;
    }

    public static String task3(String message, String key) {
        int keyLength = key.length();

        int[] keyLetterNumber = getNumbers(key);
        char[][] matrix = getMatrix(message, keyLength);
        sortMatrix(matrix, keyLetterNumber);

        return makeResult(matrix);
    }

    public static int[] getNumbers(String key) {
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        int[] keyLetterNumber = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            int indexInSortedKey = new String(sortedKey).indexOf(currentChar);
            sortedKey[indexInSortedKey] = '.';
            keyLetterNumber[i] = indexInSortedKey;
        }

        return keyLetterNumber;
    }

    public static char[][] getMatrix(String message, int keyLength) {
        String[] parts = new String[(int) Math.ceil((double) message.length() / keyLength)];

        for (int i = 0; i < parts.length; i++) {
            int startIndex = i * keyLength;
            int endIndex = Math.min((i + 1) * keyLength, message.length());
            String part = message.substring(startIndex, endIndex);
            parts[i] = part;
        }

        while (parts[parts.length - 1].length() < keyLength) {
            parts[parts.length - 1] += " ";
        }

        char[][] matrix = new char[parts.length][keyLength];
        for (int row = 0; row < parts.length; row++) {
            for (int col = 0; col < keyLength; col++) {
                matrix[row][col] = parts[row].charAt(col);
            }
        }

        return matrix;
    }

    public static void sortMatrix(char[][] matrix, int[] keyLetterNumber) {
        for (char[] chars : matrix) {
            char[] transformedRow = new char[chars.length];
            for (int col = 0; col < chars.length; col++) {
                transformedRow[keyLetterNumber[col]] = chars[col];
            }
            System.arraycopy(transformedRow, 0, chars, 0, transformedRow.length);
        }
    }

    public static String makeResult(char[][] matrix) {
        String result = "";

        for (char[] row : matrix) {
            for (char symbol : row) {
                result += symbol;
            }
        }

        return result;
    }

    public static int[] task4(int[] arr, int n) {
        int[] result = new int[2];
        int minIndexDifference = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * arr[j] == n && j - i < minIndexDifference && result[0] != arr[i] &&
                        result[0] != arr[j] && result[1] != arr[i] && result[1] != arr[i]) {
                    result[0] = arr[i];
                    result[1] = arr[j];
                    minIndexDifference = j - i;
                }
            }
        }

        if (Arrays.equals(result, new int[2])) {
            result = new int[]{};
        }
        return result;
    }

    public static int[] task5(int n) {
        int[] result = getFactorial(n, 1, 1);
        if (result == null) {
            result = new int[]{};
        }
        return result;
    }

    private static int[] getFactorial(int n, int current, int factorial) {
        if (n == factorial) {
            return new int[]{n, current};
        } else if (factorial > n) {
            return null;
        } else {
            return getFactorial(n, current + 1, factorial * (current + 1));
        }
    }

    public static String task6(String decimal) {
        Pattern pattern = Pattern.compile("(\\d+)?\\.?(\\d+)?\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(decimal);

        if (matcher.matches()) {
            String integerPart = matcher.group(1);
            String nonRepeatingPart = matcher.group(2);
            String repeatingPart = matcher.group(3);

            int numerator = 0;
            int denominator = 1;

            if (nonRepeatingPart != null) {
                numerator += Integer.parseInt(nonRepeatingPart);
                denominator *= (int) Math.pow(10, nonRepeatingPart.length());
            }

            if (repeatingPart != null) {
                numerator = numerator * (int) Math.pow(10, repeatingPart.length()) + Integer.parseInt(repeatingPart);
                denominator *= (int) (Math.pow(10, repeatingPart.length()) - 1);

                if (nonRepeatingPart != null) {
                    numerator -= Integer.parseInt(nonRepeatingPart);
                }
            }

            if (integerPart != null) {
                numerator += Integer.parseInt(integerPart) * denominator;
            }

            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;

            return numerator + "/" + denominator;
        } else {
            return "";
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static String task7(String txt) {
        if (txt.isEmpty()) {
            return "";
        }

        String piDigits = "314159265358979";
        String result = "";

        for (int i = 0; i < piDigits.length(); i++) {
            int piDigit = Character.getNumericValue(piDigits.charAt(i));
            int wordLength = Math.min(piDigit, txt.length());

            result += txt.substring(0, wordLength);
            txt = txt.substring(wordLength);

            if (piDigit > wordLength) {
                char lastChar = result.charAt(result.length() - 1);
                result += String.valueOf(lastChar).repeat(piDigit - wordLength);
            }

            if (i < piDigits.length() - 1) {
                result += ' ';
            }
        }

        return result;
    }

    public static String task8(String expression) {
        String result;
        try {
            result = Integer.toString(Integer.parseInt(calculation(expression)));
        } catch (Exception e) {
            result = "Error: " + e;
        }
        return result;
    }

    private static String calculation(String input) {
        Pattern pattern1 = Pattern.compile("-?\\d+\\s\\*\\s-?\\d+");
        Pattern pattern2 = Pattern.compile("-?\\d+\\s/\\s-?\\d+");
        Pattern pattern3 = Pattern.compile("-?\\d+\\s-\\s-?\\d+");
        Pattern pattern4 = Pattern.compile("-?\\d+\\s\\+\\s-?\\d+");
        Pattern patternD = Pattern.compile("-?\\d+");
        Pattern patternBracket = Pattern.compile("\\([0-9*/+\\-\\s]+\\)");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        Matcher matcher3 = pattern3.matcher(input);
        Matcher matcher4 = pattern4.matcher(input);
        Matcher matcherBracket = patternBracket.matcher(input);

        if (matcherBracket.find()) {
            String bracket = matcherBracket.group().replaceAll("[()]", "");
            input = input.replaceFirst(String.valueOf(patternBracket), calculation(bracket));
            return calculation(input);
        } else if (matcher1.find()) {
            int x = 1;
            Matcher matcherD = patternD.matcher(matcher1.group());
            if (matcherD.find()) {
                x = Integer.parseInt(matcherD.group());
            }
            if (matcherD.find()) {
                x *= Integer.parseInt(matcherD.group());
            }
            input = input.replaceFirst(String.valueOf(pattern1), Integer.toString(x));
            return calculation(input);
        } else if (matcher2.find()) {
            int x = 1;
            Matcher matcherD = patternD.matcher(matcher2.group());
            if (matcherD.find()) {
                x = Integer.parseInt(matcherD.group());
            }
            if (matcherD.find()) {
                try {
                    x /= Integer.parseInt(matcherD.group());
                } catch (Exception e) {
                    input = "Error: " + e;
                }
            }
            input = input.replaceFirst(String.valueOf(pattern2), Integer.toString(x));
            return calculation(input);
        } else if (matcher3.find()) {
            int x = 1;
            Matcher matcherD = patternD.matcher(matcher3.group());
            if (matcherD.find()) {
                x = Integer.parseInt(matcherD.group());
            }
            if (matcherD.find()) {
                x -= Integer.parseInt(matcherD.group());
            }
            input = input.replaceFirst(String.valueOf(pattern3), Integer.toString(x));
            return calculation(input);
        } else if (matcher4.find()) {
            int x = 1;
            Matcher matcherD = patternD.matcher(matcher4.group());
            if (matcherD.find()) {
                x = Integer.parseInt(matcherD.group());
            }
            if (matcherD.find()) {
                x += Integer.parseInt(matcherD.group());
            }
            input = input.replaceFirst(String.valueOf(pattern4), Integer.toString(x));
            return calculation(input);
        } else {
            return input;
        }
    }
    public static String task9(String str) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char ch : str.toCharArray()) {
            charFrequency.put(ch, charFrequency.getOrDefault(ch, 0) + 1);
        }

        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int freq : charFrequency.values()) {
            freqCount.put(freq, freqCount.getOrDefault(freq, 0) + 1);
        }

        if (freqCount.size() > 2) {
            return "NO";
        }

        if (freqCount.size() == 1) {
            return "YES";
        }

        int freq1 = 0;
        int freq2 = 0;
        int count1 = 0;
        int count2 = 0;

        for (Map.Entry<Integer, Integer> entry : freqCount.entrySet()) {
            if (freq1 == 0) {
                freq1 = entry.getKey();
                count1 = entry.getValue();
            } else {
                freq2 = entry.getKey();
                count2 = entry.getValue();
            }
        }

        if ((freq1 == 1 && count1 == 1) || (freq2 == 1 && count2 == 1)) {
            return "YES";
        }

        if ((freq1 - freq2 == 1 && count1 == 1) || (freq2 - freq1 == 1 && count2 == 1)) {
            return "YES";
        }

        return "NO";
    }

    public static String task10(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int index = dp[m][n];
        char[] lcs = new char[index];

        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs[--index] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }
}
