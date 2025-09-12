import java.util.*;

public class PlayfairCipher {
    private static char[][] matrix = new char[5][5];

    public static void generateKeyMatrix(String key) {
        key = key.toUpperCase().replaceAll("J", "I").replaceAll("[^A-Z]", "");
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : key.toCharArray()) set.add(c);
        for (char c = 'A'; c <= 'Z'; c++) if (c != 'J') set.add(c);

        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = it.next();
    }

    public static String[] formPairs(String input) {
        input = input.toUpperCase().replaceAll("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));
            if (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1))
                sb.append('X');
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString().split("(?<=\\G.{2})");
    }

    public static String process(String text, boolean encrypt) {
        String[] pairs = formPairs(text);
        StringBuilder result = new StringBuilder();

        for (String pair : pairs) {
            int[] pos1 = findPosition(pair.charAt(0));
            int[] pos2 = findPosition(pair.charAt(1));

            if (pos1[0] == pos2[0]) { // same row
                result.append(matrix[pos1[0]][(pos1[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (pos1[1] == pos2[1]) { // same column
                result.append(matrix[(pos1[0] + (encrypt ? 1 : 4)) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + (encrypt ? 1 : 4)) % 5][pos2[1]]);
            } else { // rectangle swap
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return result.toString();
    }

    public static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking user input
        System.out.print("Enter key: ");
        String key = sc.nextLine();

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        // Generate key matrix
        generateKeyMatrix(key);

        // Encrypt and Decrypt
        String encrypted = process(message, true);
        String decrypted = process(encrypted, false);

        System.out.println("Original : " + message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
